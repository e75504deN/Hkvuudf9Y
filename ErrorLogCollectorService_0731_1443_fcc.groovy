// 代码生成时间: 2025-07-31 14:43:15
package com.example.errorhandling

import groovy.util.logging.Slf4j
import org.apache.commons.io.FileUtils
import org.springframework.stereotype.Service

import java.text.SimpleDateFormat
import java.util.Date
import java.io.File
import java.nio.file.Paths
import java.nio.file.Files

/**
 * Service for collecting and managing error logs.
 *
 * @author Your Name
 * @since 1.0.0
 */
@Slf4j
@Service
class ErrorLogCollectorService {

    private static final String LOG_DIR = '/var/log/errorlogs/'
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss")

    /**
     * Collects error logs and saves them to a file.
     *
     * @param errorDetails The error details to be logged.
     * @return The path to the saved error log file.
     */
    String collectErrorLog(String errorDetails) {
        try {
            // Generate a unique filename based on the current timestamp.
            String filename = 'error_log_' + dateFormat.format(new Date()) + '.log'
            File logFile = new File(LOG_DIR, filename)

            // Ensure the log directory exists.
            Files.createDirectories(Paths.get(LOG_DIR))

            // Write the error details to the log file.
            logFile.withWriter { writer ->
                writer.writeLine(errorDetails)
            }

            // Return the path to the log file.
            return logFile.getAbsolutePath()
        } catch (Exception e) {
            // Log the exception and return null in case of failure.
            log.error("Failed to collect error log: \${e.message}", e)
            null
        }
    }

    /**
     * Deletes old error logs that are older than the specified number of days.
     *
     * @param days The number of days to consider logs old.
     */
    void deleteOldLogs(int days) {
        try {
            File dir = new File(LOG_DIR)
            if (dir.exists() && dir.isDirectory()) {
                // Get the current time and calculate the cutoff time.
                long now = System.currentTimeMillis()
                long cutoff = now - (days * 86400000)  // 86400000 milliseconds in a day

                // Iterate over the files in the log directory and delete old logs.
                dir.eachFile { File file ->
                    if (file.lastModified() < cutoff) {
                        FileUtils.forceDelete(file)
                    }
                }
            }
        } catch (Exception e) {
            // Log the exception.
            log.error("Failed to delete old error logs: \${e.message}", e)
        }
    }
}
