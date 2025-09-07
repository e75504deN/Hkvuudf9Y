// 代码生成时间: 2025-09-07 14:17:23
import grails.util.Environment
import groovyx.net.http.HTTPBuilder
import org.apache.http.conn.HttpHostConnectException

"""
* NetworkStatusChecker.groovy
* A Grails service to check the network connection status.
*"""

class NetworkStatusChecker {

    private final HTTPBuilder httpBuilder

    // Constructor to initialize the HTTPBuilder
    NetworkStatusChecker() {
        this.httpBuilder = new HTTPBuilder()
    }

    /**
     * Checks if the network connection is active and reachable.
     * 
     * @param url The URL to check the connectivity.
     * @return A boolean indicating the network status.
     */
    boolean isNetworkAvailable(String url) {
        try {
            // Attempt to connect to the given URL
            httpBuilder.request(Method.GET, URI.create(url)) { req ->
                // Configure request headers if necessary
                // req.headers.'Accept' = 'application/json'
                
                // Execute the request
                response.success { resp, json ->
                    // If we reach here, the network is available
                    return true
                }
                response.failure { resp ->
                    // Handle HTTP errors (e.g., 404, 500)
                    return false
                }
            }
        } catch (HttpHostConnectException e) {
            // Handle connection exceptions
            return false
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace()
            return false
        }
    }

    /**
     * Checks the network status using a predefined URL.
     * 
     * @return A boolean indicating the network status.
     */
    boolean checkDefaultNetworkStatus() {
        // Use a reliable URL to check the network status, e.g., httpbin.org
        return isNetworkAvailable('https://httpbin.org/get')
    }
}