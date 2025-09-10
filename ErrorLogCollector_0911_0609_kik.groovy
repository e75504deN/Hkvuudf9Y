// 代码生成时间: 2025-09-11 06:09:05
package com.example.logging

import groovy.util.logging.Slf4j
import org.apache.commons.io.FileUtils
import org.apache.commons.io.IOUtils
import org.grails.plugins.logging.LoggingManagerService

/**
 * ErrorLogCollector provides functionality to collect and manage error logs.
 * It adheres to Java best practices and is structured for maintainability and extensibility.
 */
@Slf4j
class ErrorLogCollector {

    /**
     * Writes an error message to a log file.
     *
     * @param errorMessage The error message to be logged.
     * @param logFileName The name of the log file where the error will be written.
     */
    void logError(String errorMessage, String logFileName) {
        try {
            File logFile = new File(logFileName)
            if (!logFile.exists()) {
                logFile.createNewFile()
            }
            FileWriter fileWriter = new FileWriter(logFileName, true)
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)
            bufferedWriter.write(errorMessage + '
')
            bufferedWriter.close()
        } catch (IOException e) {
            log.error('Failed to write error message to log file: ' + e.message)
        }
    }

    /**
     * Reads the contents of the log file.
     *
     * @param logFileName The name of the log file to be read.
     * @return The contents of the log file as a string.
     */
    String readLogFile(String logFileName) {
        try {
            return FileUtils.readFileToString(new File(logFileName), 'UTF-8')
        } catch (IOException e) {
            log.error('Failed to read log file: ' + e.message)
            return null
        }
    }

    /**
     * Deletes the specified log file.
     *
     * @param logFileName The name of the log file to be deleted.
     */
    void deleteLogFile(String logFileName) {
        try {
            File logFile = new File(logFileName)
            if (logFile.exists()) {
                logFile.delete()
            }
        } catch (Exception e) {
            log.error('Failed to delete log file: ' + e.message)
        }
    }
}
