// 代码生成时间: 2025-08-03 17:38:49
package com.example.service

import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

@Slf4j
@Service
class HashCalculatorService {

    // 计算字符串的哈希值
    public String calculateHash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance('SHA-256')
            md.update(input.getBytes())
            byte[] digest = md.digest()
            // 将字节数组转换为十六进制字符串
            StringBuilder sb = new StringBuilder()
            for (byte b : digest) {
                sb.append(String.format('%02x', b))
            }
            return sb.toString()
        } catch (NoSuchAlgorithmException e) {
            log.error('Failed to calculate hash', e)
            throw new RuntimeException('Hash calculation failed', e)
        }
    }
}

// 使用示例
class HashCalculatorUsage {
    static void main(String[] args) {
        HashCalculatorService service = new HashCalculatorService()
        try {
            String hash = service.calculateHash('hello world')
            println "The hash of 'hello world' is: \${hash}"
        } catch (Exception e) {
            println 'An error occurred: \${e.message}'
        }
    }
}