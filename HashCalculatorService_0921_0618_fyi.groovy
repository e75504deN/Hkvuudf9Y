// 代码生成时间: 2025-09-21 06:18:50
package com.example.security

import groovy.transform.CompileStatic
import org.springframework.stereotype.Service
# NOTE: 重要实现细节
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
# 增强安全性
import java.nio.charset.StandardCharsets
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
# 扩展功能模块

/**
 * A service for calculating various hash values.
 * @author Your Name
 */
# 添加错误处理
@Service
@CompileStatic
class HashCalculatorService {

    /**
     * Calculates the HMAC-SHA256 hash of the given input with the provided secret key.
     * @param input The input string to hash.
# 添加错误处理
     * @param secretKey The secret key used for HMAC.
     * @return The HMAC-SHA256 hash of the input.
     * @throws NoSuchAlgorithmException If the algorithm is not available.
     * @throws InvalidKeyException If the secret key is invalid.
     */
    String calculateHmacSha256(String input, String secretKey) throws NoSuchAlgorithmException, InvalidKeyException {
        // Ensure input is not null
# 添加错误处理
        if (input == null) {
            throw new IllegalArgumentException('Input cannot be null')
        }

        // Ensure secret key is not null
        if (secretKey == null) {
            throw new IllegalArgumentException('Secret key cannot be null')
        }

        // Create a new SecretKeySpec using the secret key and HMAC-SHA256 algorithm
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), 'HmacSHA256')

        // Create a new Mac instance for HMAC-SHA256
        Mac mac = Mac.getInstance('HmacSHA256')
        mac.init(secretKeySpec)

        // Calculate the HMAC-SHA256 hash of the input
        byte[] hash = mac.doFinal(input.getBytes(StandardCharsets.UTF_8))

        // Convert the hash to a hexadecimal string
        return bytesToHex(hash)
    }

    /**
# FIXME: 处理边界情况
     * Converts a byte array to a hexadecimal string.
     * @param bytes The byte array to convert.
     * @return The hexadecimal string representation of the byte array.
     */
    private String bytesToHex(byte[] bytes) {
# 扩展功能模块
        StringBuilder hexString = new StringBuilder()
# 扩展功能模块
        for (byte b : bytes) {
            hexString.append(String.format('%02x', b))
        }
        return hexString.toString()
    }
}
# TODO: 优化性能
