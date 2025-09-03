// 代码生成时间: 2025-09-03 17:47:00
import grails.util.GrailsNameUtils
import groovy.sql.Sql
import groovy.util.logging.Slf4j
import org.grails.datastore.mapping.core.DatastoreUtils
import org.grails.datastore.mapping.transactions.DatastoreTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.TransactionStatus
import org.springframework.transaction.support.DefaultTransactionDefinition

@Slf4j
class DatabaseMigrationTool {

    // Get the Grails application context
    def grailsApplication
    def dataSource

    // Method to run database migration
    def migrate() {
        try {
            // Get the list of migration scripts
            def migrationScripts = []
            grailsApplication.mainContext.resources.resourceLoader.resources.each { resource ->
                if (resource.file?.name?.startsWith('migration-') && resource.file?.name?.endsWith('.sql')) {
                    migrationScripts << resource.file
                }
            }

            // Sort the migration scripts based on their file names
            migrationScripts.sort { it.name }

            // Execute each migration script
            migrationScripts.each { script ->
                log.info "Executing migration script: ${script.name}"
                executeScript(script)
            }
        } catch (Exception e) {
            log.error "Error during database migration", e
            throw e
        }
    }

    // Method to execute a single migration script
    private void executeScript(File script) {
        // Get the Sql instance from the dataSource
        Sql sql = new Sql(dataSource)
        try {
            // Read the contents of the script file
            def scriptContent = script.text

            // Execute the script
            sql.execute(scriptContent)
        } catch (Exception e) {
            log.error "Error executing script: ${script.name}", e
            throw e
        } finally {
            // Close the Sql instance
            sql.close()
        }
    }
}
