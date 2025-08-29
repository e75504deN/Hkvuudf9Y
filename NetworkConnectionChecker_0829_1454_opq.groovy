// 代码生成时间: 2025-08-29 14:54:40
package com.example

import groovy.net.SocketConnectException
import java.net.HttpURLConnection
import java.net.URL

/**
 * NetworkConnectionChecker is a utility class to check the network connection status.
 * It can be used to ping a specific URL and check if a network connection is available.
 */
class NetworkConnectionChecker {

    /**
     * Checks if the network connection is available by attempting to connect to the specified URL.
     *
     * @param url The URL to check the network connection status.
     * @return true if the network connection is available, false otherwise.
     */
    boolean isNetworkAvailable(String url) {
        try {
            URL obj = new URL(url)
            HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection()
            httpURLConnection.setRequestMethod('GET')
            httpURLConnection.connectTimeout = 5000 // 5 seconds
            httpURLConnection.readTimeout = 5000 // 5 seconds
            httpURLConnection.connect()
            return httpURLConnection.responseCode == HttpURLConnection.HTTP_OK
        } catch (SocketConnectException e) {
            println 'Network connection failed: ' + e.getMessage()
            return false
        } catch (Exception e) {
            println 'An error occurred: ' + e.getMessage()
            return false
        } finally {
            // Ensure the connection is closed after checking
            httpURLConnection?.disconnect()
        }
    }

    /**
     * Main method to test the network connection checker.
     *
     * @param args The command line arguments (optional).
     */
    static void main(String[] args) {
        NetworkConnectionChecker checker = new NetworkConnectionChecker()
        boolean isConnected = checker.isNetworkAvailable('https://www.google.com')
        println "Is network available? ${isConnected}"
    }
}
