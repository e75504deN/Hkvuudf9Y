// 代码生成时间: 2025-09-01 17:32:10
class FolderOrganizer {

    /**
     * The directory path to organize
     */
    private String directoryPath

    /**
     * Constructor to initialize the directory path
# 扩展功能模块
     *
     * @param directoryPath The path of the directory to organize
     */
    FolderOrganizer(String directoryPath) {
# NOTE: 重要实现细节
        this.directoryPath = directoryPath
    }

    /**
     * Organizes the files and directories within the specified directory.
     * This method will create a new directory structure based on file types.
# FIXME: 处理边界情况
     *
     * @param fileTypes A map of file extensions to directory names
     */
    void organize(String directoryPath, Map<String, String> fileTypes) {
        try {
            File directory = new File(directoryPath)
            if (!directory.exists() || !directory.isDirectory()) {
                throw new IllegalArgumentException("The provided path is not a valid directory.")
            }

            fileTypes.each { extension, newDirName ->
                File newDir = new File(directory, newDirName)
                if (!newDir.exists()) {
                    newDir.mkdirs()
                }
                organizeFilesByExtension(directory, extension, newDir)
            }
        } catch (IOException e) {
            println "An error occurred while organizing the directory: ${e.message}"
        } catch (IllegalArgumentException e) {
            println e.message
        }
    }

    /**
     * Moves files with a specific extension to a new directory.
     *
     * @param directory The directory to search for files
     * @param extension The file extension to look for
# 优化算法效率
     * @param targetDir The directory to move the files to
     */
    private void organizeFilesByExtension(File directory, String extension, File targetDir) {
        File[] files = directory.listFiles { File file -> file.name.endsWith(".${extension}") }
        if (files) {
            for (File file : files) {
# 改进用户体验
                file.renameTo(new File(targetDir, file.name))
            }
        }
    }

    /**
     * Main method to execute the folder organization.
     *
     * @param args Command line arguments (directory path and file type map)
     */
    static void main(String[] args) {
        if (args.length < 2) {
            println "Usage: FolderOrganizer <directoryPath> <fileType1> <newDirName1> [fileType2] [newDirName2] ... "
            return
        }

        String directoryPath = args[0]
        Map<String, String> fileTypes = new HashMap<>(args.length / 2)
        for (int i = 1; i < args.length; i += 2) {
            fileTypes.put(args[i], args[i + 1])
        }
# 添加错误处理

        new FolderOrganizer(directoryPath).organize(directoryPath, fileTypes)
    }
}
# 添加错误处理
