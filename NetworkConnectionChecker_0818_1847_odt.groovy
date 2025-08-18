// 代码生成时间: 2025-08-18 18:47:35
import grails.async.Promise
grails.util.Environment
grails.util.Metadata
import java.net.HttpURLConnection
import java.net.URL

/**
 * NetworkConnectionChecker is a utility class that checks the
 * network connectivity by attempting to reach a specified URL.
 */
class NetworkConnectionChecker {

    // URL to check for network connectivity
    private static final String CHECK_URL = 'http://www.google.com'

    /**
     * Checks the network connectivity by attempting to reach a specified URL.
     *
     * @return A Promise that resolves to a boolean indicating whether the connection
     *         was successful or not.
     */
    static Promise<Boolean> checkConnection() {
        return Promise.create {
            try {
                URL url = new URL(CHECK_URL)
                HttpURLConnection connection = (HttpURLConnection) url.openConnection()
                connection.setRequestMethod('GET')
                connection.setConnectTimeout(5000) // 5 seconds timeout
                connection.setReadTimeout(5000)

                int responseCode = connection.getResponseCode()
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    return true
                } else {
                    return false
                }
            } catch (IOException e) {
                // Log the exception and return false
                e.printStackTrace()
                return false
            } finally {
                // Ensure the connection is closed
                if (connection) {
                    connection.disconnect()
                }
            }
        }
    }

    /**
     * Main method to run the NetworkConnectionChecker.
     *
     * @param args Command line arguments (not used in this example).
     */
    static void main(String[] args) {
        // Check the network connection and print the result
        checkConnection().then {
            println "Network connection is ${it ? 'available' : 'not available'}"
        }
    }
}
