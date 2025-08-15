// 代码生成时间: 2025-08-15 22:24:46
package tools

import groovy.io.FileType
import groovy.io.FileVisitResult
import groovy.io.FilenameFilter
import groovy.transform.CompileStatic

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

/**
 * A utility class for file backup and synchronization.
 * This class provides methods to backup and sync files between directories.
 */
@CompileStatic
class FileBackupAndSyncTool {

    // Source directory path
    private Path sourceDir
    // Destination directory path
    private Path destDir
    // File backup extension
    private static final String BACKUP_EXTENSION = ".backup"

    /**
     * Constructor
     * @param sourceDirPath The path of the source directory
     * @param destDirPath The path of the destination directory
     */
    FileBackupAndSyncTool(String sourceDirPath, String destDirPath) {
        this.sourceDir = Paths.get(sourceDirPath)
        this.destDir = Paths.get(destDirPath)
    }

    /**
     * Backup and sync files from source directory to destination directory.
     * @param fileFilter The filter to select files for backup and sync
     * @return The count of files backed up and synced
     */
    int backupAndSyncFiles(FilenameFilter fileFilter) {
        int count = 0
        sourceDir.eachFileRecurse(FileType.FILES) { File file ->
            if (fileFilter.accept(file.parentFile, file.name)) {
                try {
                    // Create backup file
                    createBackup(file)
                    // Copy file to destination directory
                    copyFileToDestination(file)
                    count++
                } catch (IOException e) {
                    // Handle file copy error
                    println "Error copying file ${file.name}: ${e.message}"
                }
            }
        }
        return count
    }

    /**
     * Create a backup file for the given file.
     * @param file The file to create a backup for
     */
    private void createBackup(File file) {
        Path backupFilePath = sourceDir.resolve(file.name + BACKUP_EXTENSION)
        Files.copy(file.toPath(), backupFilePath, StandardCopyOption.REPLACE_EXISTING)
    }

    /**
     * Copy the file to the destination directory.
     * @param file The file to copy
     */
    private void copyFileToDestination(File file) {
        Path destFilePath = destDir.resolve(file.name)
        Files.copy(file.toPath(), destFilePath, StandardCopyOption.REPLACE_EXISTING)
    }

    /**
     * Main method to run the backup and sync tool.
     * @param args Command line arguments
     */
    static void main(String[] args) {
        // Define source and destination directories
        String sourceDirPath = "/path/to/source/directory"
        String destDirPath = "/path/to/destination/directory"

        // Define a filter to select files for backup and sync
        FilenameFilter fileFilter = { dir, name -> name.endsWith(".txt") }

        // Create an instance of the tool and run backup and sync
        FileBackupAndSyncTool tool = new FileBackupAndSyncTool(sourceDirPath, destDirPath)
        int filesBackedUp = tool.backupAndSyncFiles(fileFilter)
        println "Backed up and synced ${filesBackedUp} files."
    }
}
