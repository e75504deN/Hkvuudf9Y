// 代码生成时间: 2025-08-03 06:52:18
import org.apache.commons.compress.archivers.ArchiveEntry
import org.apache.commons.compress.archivers.ArchiveInputStream
import org.apache.commons.compress.archivers.ArchiveStreamFactory
import org.apache.commons.compress.archivers.tar.TarArchiveEntry
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream
import org.apache.commons.compress.utils.IOUtils
import org.apache.commons.compress.utils.ListArchiveEntry
import org.apache.commons.compress.utils.SeekableInMemoryByteChannel
import java.io.*

// 定义一个DecompressionTool类来作为压缩文件解压工具
class DecompressionTool {

    // 解压ZIP文件的方法
    void unzip(String zipFilePath, String destDir) {
        try {
            File zipFile = new File(zipFilePath)
            ArchiveInputStream zipIn = new ArchiveStreamFactory().createArchiveInputStream(ZipArchiveEntry.class, new FileInputStream(zipFile))
            ArchiveEntry entry = zipIn.getNextEntry()
            while (entry != null) {
                File newFile = newFile(destDir, entry)
                if (!entry.isDirectory()) {
                    extractFile(zipIn, newFile)
                } else {
                    newFile.mkdirs()
                }
                zipIn.closeEntry()
                entry = zipIn.getNextEntry()
            }
            zipIn.close()
        } catch (IOException e) {
            println "Error occurred while unzipping: $e.message"
        }
    }

    // 解压Tar文件的方法
    void untar(String tarFilePath, String destDir) {
        try {
            File tarFile = new File(tarFilePath)
            ArchiveInputStream tarIn = new ArchiveStreamFactory().createArchiveInputStream(TarArchiveEntry.class, new FileInputStream(tarFile))
            ArchiveEntry entry = tarIn.getNextEntry()
            while (entry != null) {
                File newFile = newFile(destDir, entry)
                if (!entry.isDirectory()) {
                    extractFile(tarIn, newFile)
                } else {
                    newFile.mkdirs()
                }
                tarIn.closeEntry()
                entry = tarIn.getNextEntry()
            }
            tarIn.close()
        } catch (IOException e) {
            println "Error occurred while untarring: $e.message"
        }
    }

    // 解压Gzip文件的方法
    void ungzip(String gzipFilePath, String destFilePath) {
        try {
            FileInputStream fis = new FileInputStream(gzipFilePath)
            GzipCompressorInputStream gis = new GzipCompressorInputStream(fis)
            FileOutputStream fos = new FileOutputStream(destFilePath)
            IOUtils.copy(gis, fos)
            fos.close()
            gis.close()
            fis.close()
        } catch (IOException e) {
            println "Error occurred while ungzipping: $e.message"
        }
    }

    // 解压Bzip2文件的方法
    void unbzip2(String bzip2FilePath, String destFilePath) {
        try {
            FileInputStream fis = new FileInputStream(bzip2FilePath)
            BZip2CompressorInputStream bis = new BZip2CompressorInputStream(fis)
            FileOutputStream fos = new FileOutputStream(destFilePath)
            IOUtils.copy(bis, fos)
            fos.close()
            bis.close()
            fis.close()
        } catch (IOException e) {
            println "Error occurred while unbzipping: $e.message"
        }
    }

    // 辅助方法用于创建新文件
    private File newFile(String destDir, ArchiveEntry entry) {
        File destFile = new File(destDir + File.separator + entry.getName())
        return destFile
    }

    // 辅助方法用于提取文件
    private void extractFile(ArchiveInputStream archiveIn, File newFile) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(newFile))
        IOUtils.copy(archiveIn, bos)
        bos.close()
    }
}
