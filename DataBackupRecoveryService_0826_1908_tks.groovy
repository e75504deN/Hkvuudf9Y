// 代码生成时间: 2025-08-26 19:08:03
package com.example.backup

import groovy.util.logging.Slf4j
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream
import org.apache.commons.compress.compress.archivers.tar.TarArchiveEntry
import org.apache.commons.compress.compress.utils.IOUtils
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.nio.file.Path
import java.io.*

/**
 * Service class to handle data backup and recovery operations.
 */
@Slf4j
class DataBackupRecoveryService {

    /**
     * Backs up the specified directory into a tar.gz file.
     *
     * @param sourceDir The directory to backup.
     * @param backupFile The file path to save the backup.
     * @return A boolean indicating the success of the operation.
     */
    boolean backupDirectory(String sourceDir, String backupFile) {
        try {
            File sourceDirFile = new File(sourceDir)
            if (!sourceDirFile.exists() || !sourceDirFile.isDirectory()) {
                log.error("Source directory does not exist or is not a directory: ${sourceDir}")
                return false
            }

            OutputStream fileOutputStream = new FileOutputStream(backupFile)
            OutputStream gzipOutputStream = new GZIPOutputStream(fileOutputStream)
            TarArchiveOutputStream tarOutputStream = new TarArchiveOutputStream(gzipOutputStream)

            tarOutputStream.setLongFileMode(TarArchiveOutputStream.LONGFILE_GNU)
            createTarGzFile(sourceDir, tarOutputStream, new File(sourceDir), "")
            tarOutputStream.close()
            gzipOutputStream.close()
            fileOutputStream.close()

            log.info("Backup completed successfully. File saved to: ${backupFile}")
            return true
        } catch (Exception e) {
            log.error("Error during backup: ${e.message}", e)
            return false
        }
    }

    /**
     * Creates a tar.gz file from a directory.
     *
     * @param basePath The base path of the directory to backup.
     * @param tarOutputStream The TarArchiveOutputStream to write to.
     * @param file The file or directory to add to the tar.
     * @param baseName The base name of the file in the tar.
     * @throws IOException If an I/O error occurs.
     */
    private void createTarGzFile(String basePath, TarArchiveOutputStream tarOutputStream, File file, String baseName) throws IOException {
        if (file.isDirectory()) {
            File[] files = file.listFiles()
            if (files != null) {
                for (File child : files) {
                    createTarGzFile(basePath, tarOutputStream, child, baseName + file.name + '/')
                }
            }
        } else {
            TarArchiveEntry tarEntry = new TarArchiveEntry(file, baseName + file.name)
            tarOutputStream.putArchiveEntry(tarEntry)
            Files.copy(file.toPath(), tarOutputStream)
            tarOutputStream.closeArchiveEntry()
        }
    }

    /**
     * Recovers data from a tar.gz backup file to the specified directory.
     *
     * @param backupFile The backup file to recover from.
     * @param targetDir The directory to recover data to.
     * @return A boolean indicating the success of the operation.
     */
    boolean recoverData(String backupFile, String targetDir) {
        try {
            File backupFileObject = new File(backupFile)
            if (!backupFileObject.exists()) {
                log.error("Backup file does not exist: ${backupFile}")
                return false
            }

            File targetDirObject = new File(targetDir)
            if (!targetDirObject.exists()) {
                targetDirObject.mkdirs()
            }

            try (InputStream fileInputStream = new FileInputStream(backupFile)) {
                try (GZIPInputStream gzipInputStream = new GZIPInputStream(fileInputStream)) {
                    try (TarArchiveInputStream tarInputStream = new TarArchiveInputStream(gzipInputStream)) {
                        TarArchiveEntry tarEntry
                        while ((tarEntry = tarInputStream.getNextTarEntry()) != null) {
                            File outputFile = new File(targetDir, tarEntry.getName())

                            if (tarEntry.isDirectory()) {
                                outputFile.mkdirs()
                            } else {
                                outputFile.getParentFile().mkdirs()
                                Files.copy(tarInputStream, outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING)
                            }
                        }
                    }
                }
            }

            log.info("Data recovery completed successfully. Data recovered to: ${targetDir}")
            return true
        } catch (Exception e) {
            log.error("Error during data recovery: ${e.message}", e)
            return false
        }
    }
}
