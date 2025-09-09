// 代码生成时间: 2025-09-09 18:38:30
package com.example

import groovy.transform.CompileStatic
import org.springframework.stereotype.Service
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * Hash Calculator Service provides functionality to calculate hash values for strings.
 * @author Your Name
 */
@Service
@CompileStatic
class HashCalculatorService {

    /**
     * Calculates the hash value of a given string.
     * @param input The string to calculate the hash for.
     * @param algorithm The hashing algorithm to use.
     * @return The hash value as a hexadecimal string.
     * @throws NoSuchAlgorithmException If the specified algorithm is not available.
     */
    String calculateHash(String input, String algorithm) throws NoSuchAlgorithmException {
        // Get an instance of the MessageDigest with the specified algorithm
        MessageDigest md = MessageDigest.getInstance(algorithm)

        // Convert the input string to bytes
        byte[] message = input.getBytes(StandardCharsets.UTF_8)

        // Perform the hashing computation
        byte[] digest = md.digest(message)

        // Convert the byte array to a hexadecimal string
        StringBuilder sb = new StringBuilder("")
        for (byte b : digest) {
            sb.append(String.format("%02x", b))
        }

        // Return the calculated hash
        return sb.toString()
    }

    // Additional methods or service logic can be added here.
}
