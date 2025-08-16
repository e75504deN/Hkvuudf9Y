// 代码生成时间: 2025-08-16 20:59:24
package com.example.service;

import grails.transaction.Transactional;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Service class for formatting API responses.
 * This service provides a standardized way to return API responses,
 * including error handling and status codes.
 */
@Transactional
public class ApiResponseFormatterService {

    /**
     * Constructs a successful API response.
     *
     * @param data The data to be included in the response body.
     * @param <T>  The type of the data.
     * @return A formatted API response with a 200 status code and the provided data.
     */
    public <T> Map<String, Object> successResponse(T data) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.OK);
        response.put("data", data);
        return response;
    }

    /**
     * Constructs an API response with an error.
     *
     * @param errorCode The error code to be included in the response.
     * @param errorMessage The error message to be included in the response.
     * @return A formatted API response with a 400 status code and the provided error details.
     */
    public Map<String, Object> errorResponse(int errorCode, String errorMessage) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST);
        response.put("error_code", errorCode);
        response.put("error_message", errorMessage);
        return response;
    }

    /**
     * Constructs an API response for not found resources.
     *
     * @return A formatted API response with a 404 status code indicating the resource was not found.
     */
    public Map<String, Object> notFoundResponse() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.NOT_FOUND);
        response.put("error_message", "Resource not found");
        return response;
    }

    /**
     * Constructs an API response for internal server errors.
     *
     * @param errorMessage The error message to be included in the response.
     * @return A formatted API response with a 500 status code and the provided error message.
     */
    public Map<String, Object> internalErrorResponse(String errorMessage) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        response.put("error_message", errorMessage);
        return response;
    }
}