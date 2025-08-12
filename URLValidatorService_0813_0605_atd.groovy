// 代码生成时间: 2025-08-13 06:05:10
package services

import grails.transaction.Transactional
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.HttpResponse
import org.apache.http.HttpStatus
import org.apache.http.util.EntityUtils
import groovy.transform.CompileStatic

/**
 * Service class for validating the validity of a URL
 */
@Transactional
@CompileStatic
class URLValidatorService {

    /**
     * Validates if the provided URL is valid and accessible.
     *
     * @param url The URL to validate.
     * @return A boolean indicating if the URL is valid and accessible.
     */
    boolean isValidURL(String url) {
        if (!url) {
            // If the URL is null or empty, it is not valid.
            return false
        }

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url)
            HttpResponse response = httpClient.execute(request)

            // Check if the HTTP response status code is in the 2xx range.
            return response.statusLine.statusCode in HttpStatus.SC_OK..(HttpStatus.SC_MULTIPLE_CHOICES - 1)
        } catch (Exception e) {
            // If any exception occurs, the URL is not valid.
            return false
        }
    }

    /**
     * Fetches the content of a valid URL and returns it as a String.
     *
     * @param url The URL to fetch content from.
     * @return The content of the URL as a String, or null if the URL is not valid.
     */
    String fetchURLContent(String url) {
        if (isValidURL(url)) {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                HttpGet request = new HttpGet(url)
                HttpResponse response = httpClient.execute(request)
                return EntityUtils.toString(response.entity)
            } catch (Exception e) {
                // In case of any exception, return null.
                return null
            }
        } else {
            return null
        }
    }
}
