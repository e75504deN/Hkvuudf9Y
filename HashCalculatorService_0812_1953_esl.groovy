// 代码生成时间: 2025-08-12 19:53:45
package com.example
# 优化算法效率

import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

/**
 * Service class for calculating hash values.
 */
@Slf4j
@Service
class HashCalculatorService {

    /**
     * Calculates the MD5 hash of the provided string.
     *
# 添加错误处理
     * @param input The input string to hash.
# 增强安全性
     * @return The MD5 hash as a hexadecimal string.
     * @throws NoSuchAlgorithmException If the MD5 algorithm is not available.
# TODO: 优化性能
     */
    String calculateMD5(String input) throws NoSuchAlgorithmException {
        return calculateHash('MD5', input)
    }

    /**
     * Calculates the SHA-256 hash of the provided string.
     *
     * @param input The input string to hash.
# 优化算法效率
     * @return The SHA-256 hash as a hexadecimal string.
     * @throws NoSuchAlgorithmException If the SHA-256 algorithm is not available.
     */
    String calculateSHA256(String input) throws NoSuchAlgorithmException {
        return calculateHash('SHA-256', input)
    }

    /**
# TODO: 优化性能
     * Calculates the HMAC-SHA-256 hash of the provided string with the given secret key.
     *
     * @param input The input string to hash.
     * @param secretKey The secret key for HMAC.
     * @return The HMAC-SHA-256 hash as a hexadecimal string.
     * @throws NoSuchAlgorithmException If the HMAC-SHA-256 algorithm is not available.
# FIXME: 处理边界情况
     * @throws IllegalArgumentException If the secret key is null or empty.
     */
    String calculateHMACSHA256(String input, String secretKey) throws NoSuchAlgorithmException, IllegalArgumentException {
        if (secretKey == null || secretKey.isEmpty()) {
            throw new IllegalArgumentException('Secret key must not be null or empty.')
        }
# 增强安全性

        Mac mac = Mac.getInstance('HmacSHA256')
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), 'HmacSHA256')
        mac.init(secretKeySpec)
        byte[] bytes = mac.doFinal(input.getBytes())
        StringBuilder sb = new StringBuilder()
        for (byte b : bytes) {
            sb.append(String.format('%02x', b))
# 添加错误处理
        }
        return sb.toString()
    }

    /**
# 添加错误处理
     * Calculates the hash of the provided string using the specified algorithm.
     *
     * @param algorithm The name of the algorithm to use.
     * @param input The input string to hash.
# 改进用户体验
     * @return The hash as a hexadecimal string.
     * @throws NoSuchAlgorithmException If the algorithm is not available.
     */
    private String calculateHash(String algorithm, String input) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm)
        byte[] hashBytes = messageDigest.digest(input.getBytes())
        StringBuilder sb = new StringBuilder()
        for (byte b : hashBytes) {
            sb.append(String.format('%02x', b))
        }
        return sb.toString()
    }
}
# TODO: 优化性能
