// 代码生成时间: 2025-09-06 01:59:56
package com.example

import grails.transaction.Transactional

@Transactional
class ApiResponseFormatter {

    private static final String DEFAULT_SUCCESS_MESSAGE = "Operation successful"
    private static final String DEFAULT_ERROR_MESSAGE = "An error occurred"

    // Formats a success response
    def formatSuccessResponse(Map data = [:]) {
        return formatResponse(true, DEFAULT_SUCCESS_MESSAGE, data)
    }

    // Formats an error response
    def formatErrorResponse(String message = DEFAULT_ERROR_MESSAGE, Map data = [:]) {
        return formatResponse(false, message, data)
    }

    // Formats a generic response
    def formatResponse(boolean success, String message, Map data = [:]) {
        Map response = [
            success: success,
            message: message,
            data: data
        ]
        return response
    }

    // Handles exceptions and formats the error response
    def handleException(Exception e) {
        log.error("Error occurred: \${e.message}", e)
        return formatErrorResponse("Error: \${e.message}")
    }
}
