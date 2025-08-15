// 代码生成时间: 2025-08-16 05:36:19
package com.example.errorlog

import groovy.util.logging.Slf4j
import org.apache.commons.io.FileUtils
import org.apache.commons.io.IOUtils
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.nio.charset.StandardCharsets

@Slf4j // 使用logback logger
class ErrorLogCollector {

    // 保存错误日志的目录路径
    private String logDirectory = 'logs/error/'

    // 构造函数，初始化错误日志目录
    ErrorLogCollector() {
        File directory = new File(logDirectory)
        if (!directory.exists()) {
            directory.mkdirs()
        }
    }

    // 收集错误日志
    void collectErrorLogs(String applicationId) {
        try {
            // 创建文件名
            String fileName = logDirectory + applicationId + '_error.log'
            File logFile = new File(fileName)
            if (!logFile.exists()) {
                logFile.createNewFile()
            }

            // 打开文件并写入错误信息
            withFile(logFile, 'a') { writer ->
                writer.write(Date.format('yyyy-MM-dd HH:mm:ss', new Date()))
                writer.write(' ERROR OCCURRED IN APPLICATION ' + applicationId + '
')
            }
        } catch (Exception e) {
            log.error('Error occurred while collecting logs: ' + e.message)
        }
    }

    // 读取错误日志
    String readErrorLogs(String applicationId) {
        try {
            // 创建文件路径
            String fileName = logDirectory + applicationId + '_error.log'
            File logFile = new File(fileName)
            if (!logFile.exists()) {
                return 'No error logs found for application ' + applicationId
            }

            // 读取文件内容
            return FileUtils.readFileToString(logFile, StandardCharsets.UTF_8)
        } catch (IOException e) {
            log.error('Error occurred while reading logs: ' + e.message)
            return 'Error occurred while reading logs'
        }
    }

    // 删除错误日志
    void deleteErrorLogs(String applicationId) {
        try {
            // 创建文件路径
            String fileName = logDirectory + applicationId + '_error.log'
            File logFile = new File(fileName)
            if (logFile.exists()) {
                boolean isDeleted = logFile.delete()
                if (!isDeleted) {
                    log.warn('Failed to delete error logs for application ' + applicationId)
                }
            }
        } catch (Exception e) {
            log.error('Error occurred while deleting logs: ' + e.message)
        }
    }

    // 清理旧的错误日志
    void cleanupOldLogs(int maxDays) {
        try {
            File dir = new File(logDirectory)
            if (dir.exists()) {
                File[] files = dir.listFiles() {
                    File file, String name ->
                        return name.endsWith('_error.log')
                }
                if (files) {
                    files.each { file ->
                        long diff = new Date().time - file.lastModified()
                        if (diff > maxDays * 24 * 60 * 60 * 1000) {
                            boolean isDeleted = file.delete()
                            if (!isDeleted) {
                                log.warn('Failed to delete old log file: ' + file.name)
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error('Error occurred while cleaning up old logs: ' + e.message)
        }
    }
}
