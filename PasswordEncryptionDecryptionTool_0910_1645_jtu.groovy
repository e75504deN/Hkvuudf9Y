// 代码生成时间: 2025-09-10 16:45:05
package com.example.security

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

/**
 * Password encryption and decryption tool using Grails framework.
 * This class provides methods to encrypt and decrypt passwords.
 */
class PasswordEncryptionDecryptionTool {

    /**
     * Encrypts the given password using BCrypt algorithm.
     *
     * @param password plaintext password to be encrypted
     * @return encrypted password
     */
    def encryptPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException('Password cannot be null or empty')
        }
        new BCryptPasswordEncoder().encode(password)
    }

    /**
     * Decrypts the given encrypted password using BCrypt algorithm.
     * Note that with BCrypt, decryption is not possible. This method
     * is provided to check if the provided encrypted password matches
     * the plaintext password.
     *
     * @param encryptedPassword encrypted password to be checked
     * @param password plaintext password to compare with
     * @return true if encrypted password matches the plaintext password, false otherwise
     */
    boolean decryptPassword(String encryptedPassword, String password) {
        if (encryptedPassword == null || encryptedPassword.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException('Encrypted password and password cannot be null or empty')
        }
        new BCryptPasswordEncoder().matches(password, encryptedPassword)
    }
}
