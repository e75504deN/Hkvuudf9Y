// 代码生成时间: 2025-09-01 07:11:00
package com.example.security

import groovy.transform.CompileStatic
import org.grails.spring.beans.factory.groovy.AutowiredAnnotationPostProcessor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * SecurityAuditLog class is responsible for logging security audit events.
 * It uses SLF4J for logging and provides methods to log various security events.
 */
@Component
@CompileStatic
class SecurityAuditLog {
    // Inject the logger
    @Autowired
    Logger log

    // Constructor
    SecurityAuditLog() {
        // Initialize the logger with the class name
        this.log = LoggerFactory.getLogger(SecurityAuditLog)
    }

    /**
     * Logs an INFO level security event.
     *
     * @param message The message to log.
     */
    void info(String message) {
        log.info(message)
    }

    /**
     * Logs a WARNING level security event.
     *
     * @param message The message to log.
     */
    void warn(String message) {
        log.warn(message)
    }

    /**
     * Logs an ERROR level security event.
     *
     * @param message The message to log.
     * @param exception The exception that caused the error.
     */
    void error(String message, Throwable exception) {
        log.error(message, exception)
    }

    /**
     * Logs a security event with additional context.
     *
     * @param level The log level.
     * @param message The message to log.
     * @param context Additional context information.
     */
    void logSecurityEvent(LogLevel level, String message, Map context = Collections.emptyMap()) {
        switch (level) {
            case LogLevel.INFO:
                info(message)
                break
            case LogLevel.WARN:
                warn(message)
                break
            case LogLevel.ERROR:
                error(message, new Exception())
                break
            default:
                throw new IllegalArgumentException("Unsupported log level: ${level}")
        }
        // Additional context can be logged here if needed
    }
}

/**
 * Enum for log levels used in security logging.
 */
enum LogLevel {
    INFO, WARN, ERROR
}