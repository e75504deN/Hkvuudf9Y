// 代码生成时间: 2025-09-07 02:19:54
 * A simple Grails service that calculates hash values for given input strings.
 *
 * @author Your Name
 * @since 2023-04-01
 */

package com.example

import groovy.transform.CompileStatic
import org.apache.commons.codec.digest.DigestUtils

@CompileStatic
class HashValueCalculator {
    // Calculate the MD5 hash of the input string
    def calculateMD5(String input) {
        try {
            return DigestUtils.md5Hex(input)
        } catch (Exception e) {
            // Handle any exceptions that might occur during the hash calculation
            throw new RuntimeException("Error calculating MD5 hash: \${e.message}", e)
        }
    }

    // Calculate the SHA-1 hash of the input string
    def calculateSHA1(String input) {
        try {
            return DigestUtils.sha1Hex(input)
        } catch (Exception e) {
            // Handle any exceptions that might occur during the hash calculation
            throw new RuntimeException("Error calculating SHA-1 hash: \${e.message}", e)
        }
    }

    // Calculate the SHA-256 hash of the input string
    def calculateSHA256(String input) {
        try {
            return DigestUtils.sha256Hex(input)
        } catch (Exception e) {
            // Handle any exceptions that might occur during the hash calculation
            throw new RuntimeException("Error calculating SHA-256 hash: \${e.message}", e)
        }
    }

    // Add more hashing methods as needed, following the same pattern
}
