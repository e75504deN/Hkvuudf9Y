// 代码生成时间: 2025-08-10 07:28:14
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream
import org.apache.commons.compress.utils.IOUtils
import java.nio.file.Paths
import java.nio.file.Files
import java.nio.file.Path

class UnzipTool {

    /**
     * Unzips a file to a specified directory
     *
     * @param zipFilePath The path to the zip file
     * @param outputDirectory The directory to extract the zip file to
     * @return A list of extracted file paths
     */
    List<String> unzip(String zipFilePath, String outputDirectory) {
        try {
            List<String> extractedFiles = new ArrayList<>()
            Path outputPath = Paths.get(outputDirectory)

            // Ensure the output directory exists
            Files.createDirectories(outputPath)

            // Open the zip file for reading
            try (ZipArchiveInputStream zipIn = new ZipArchiveInputStream(new FileInputStream(zipFilePath))) {
                ZipArchiveEntry entry = zipIn.getNextZipEntry()
                while (entry != null) {
                    Path filePath = outputPath.resolve(entry.getName())
                    File file = filePath.toFile()

                    // Create parent directories if necessary
                    file.getParentFile().mkdirs()

                    // Extract file content
                    extractedFiles << filePath.toString()
                    if (!entry.isDirectory()) {
                        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
                            IOUtils.copy(zipIn, out)
                        }
                    }
                    zipIn.closeEntry()
                    entry = zipIn.getNextZipEntry()
                }
            }
            return extractedFiles
        } catch (Exception e) {
            // Log and rethrow the exception for error handling
            e.printStackTrace()
            throw new RuntimeException("Failed to unzip file: ${e.message}")
        }
    }
}
