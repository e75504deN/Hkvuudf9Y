// 代码生成时间: 2025-08-02 05:13:46
class BackupAndRestoreService {

    // Logger for logging backup and restore operations
    private static final Logger logger = LoggerFactory.getLogger(BackupAndRestoreService)

    // Dependency injection for FileService
    def fileService

    // Method to backup data to a specified location
    def backupData(def data, String backupLocation) {
        try {
            // Check if backup location is valid
            if (!fileService.isValidDirectory(backupLocation)) {
                throw new IllegalArgumentException('Invalid backup location')
            }

            // Create a timestamped backup file name
            String timestamp = new Date().format('yyyy-MM-dd_HH-mm-ss')
            String backupFileName = "backup_${timestamp}.zip"
            String backupFilePath = "${backupLocation}/${backupFileName}"

            // Backup data to a zip file
            fileService.zipData(data, backupFilePath)
            logger.info("Data backup completed successfully. File path: ${backupFilePath}")
            return backupFilePath
        } catch (Exception e) {
            logger.error("Error occurred during data backup: ${e.message}")
            throw new RuntimeException('Data backup failed', e)
        }
    }

    // Method to restore data from a specified backup file
    def restoreData(String backupFilePath) {
        try {
            // Check if backup file exists
            if (!fileService.fileExists(backupFilePath)) {
                throw new FileNotFoundException('Backup file not found')
            }

            // Unzip the backup file to restore data
            def restoredData = fileService.unzipData(backupFilePath)
            logger.info("Data restore completed successfully. Restored data: ${restoredData}")
            return restoredData
        } catch (Exception e) {
            logger.error("Error occurred during data restore: ${e.message}")
            throw new RuntimeException('Data restore failed', e)
        }
    }
}
