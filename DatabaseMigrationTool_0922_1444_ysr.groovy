// 代码生成时间: 2025-09-22 14:44:19
class DatabaseMigrationTool {

    /**
     * 执行数据库迁移。
     *
     * @param migrationVersion 要迁移到的版本号。
     */
    def migrateDatabase(def migrationVersion) {
        try {
            // 检查迁移版本号是否有效
            if (migrationVersion == null || migrationVersion.trim().isEmpty()) {
                throw new IllegalArgumentException('Migration version cannot be null or empty')
            }

            // 获取数据库迁移服务
            def migrationService = applicationCtx.getBean('databaseMigrationService')
            
            // 执行迁移
            migrationService.migrate(migrationVersion)
            println "Database migration to version ${migrationVersion} completed successfully."
        } catch (Exception e) {
            // 处理迁移过程中的异常
            println "Error occurred during database migration: ${e.message}"
            e.printStackTrace()
        }
    }

    /**
     * 回滚数据库迁移。
     *
     * @param migrationVersion 要回滚到的版本号。
     */
    def rollbackDatabase(def migrationVersion) {
        try {
            // 检查回滚版本号是否有效
            if (migrationVersion == null || migrationVersion.trim().isEmpty()) {
                throw new IllegalArgumentException('Migration version cannot be null or empty')
            }

            // 获取数据库迁移服务
            def migrationService = applicationCtx.getBean('databaseMigrationService')
            
            // 执行回滚
            migrationService.rollback(migrationVersion)
            println "Database rollback to version ${migrationVersion} completed successfully."
        } catch (Exception e) {
            // 处理回滚过程中的异常
            println "Error occurred during database rollback: ${e.message}"
            e.printStackTrace()
        }
    }
}
