// 代码生成时间: 2025-08-26 12:34:25
class DocumentConverterService {

    /**
     * Converts a document from one format to another.
     *
     * @param sourceFile The file to be converted.
     * @param targetFormat The desired format to convert the document to.
     * @return The converted file or an error message.
     * @throws IOException If an I/O error occurs.
     * @throws IllegalArgumentException If the sourceFile or targetFormat is not supported.
     */
    def convertDocument(File sourceFile, String targetFormat) {
        if (!sourceFile) {
            throw new IllegalArgumentException('Source file cannot be null.')
        }
        if (!supportedFormats.contains(targetFormat)) {
            throw new IllegalArgumentException("Target format '${targetFormat}' is not supported.")
        }

        try {
            // Perform the conversion logic here.
            // This is a placeholder for the actual conversion implementation.
            File convertedFile = new File(sourceFile.parent, sourceFile.name.replace(sourceFile.extension, targetFormat))
            // Simulate conversion by copying the file to a new location with the target extension.
            convertedFile.bytes = sourceFile.bytes
            return convertedFile
        } catch (IOException e) {
            throw new IOException("An I/O error occurred during document conversion: ${e.message}", e)
        }
    }

    /**
     * A list of supported target formats for document conversion.
     */
    private List<String> supportedFormats = ['pdf', 'docx', 'txt']

    /**
     * Validates if the source file is readable.
     *
     * @param file The file to check.
     * @return True if the file is readable, false otherwise.
     */
    private boolean isFileReadable(File file) {
        return file.canRead()
    }
}
