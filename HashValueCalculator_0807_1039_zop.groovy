// 代码生成时间: 2025-08-07 10:39:10
package com.example

import org.springframework.util.DigestUtils

/**
 * 哈希值计算工具类，用于生成各种哈希值。
 *
 * @author Your Name
 * @version 1.0
 */
class HashValueCalculator {

    /**
     * 计算给定字符串的MD5哈希值。
     *
     * @param input 待计算哈希的字符串
     * @return MD5哈希值，如果输入为null则返回null
     */
    def calculateMD5(String input) {
        input ? DigestUtils.md5DigestAsHex(input.bytes) : null
    }

    /**
     * 计算给定字符串的SHA-256哈希值。
     *
     * @param input 待计算哈希的字符串
     * @return SHA-256哈希值，如果输入为null则返回null
     */
    def calculateSHA256(String input) {
        input ? DigestUtils.sha256DigestAsHex(input.bytes) : null
    }

    /**
     * 计算给定字符串的SHA-1哈希值。
     *
     * @param input 待计算哈希的字符串
     * @return SHA-1哈希值，如果输入为null则返回null
     */
    def calculateSHA1(String input) {
        input ? DigestUtils.shaDigestAsHex(input.bytes) : null
    }
}
