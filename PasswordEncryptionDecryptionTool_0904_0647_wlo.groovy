// 代码生成时间: 2025-09-04 06:47:30
package com.example.security

import groovy.util.logging.Slf4j
import org.springframework.stereotype.Component
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec
import java.security.SecureRandom
import java.util.Base64

/**
 * A utility class for password encryption and decryption.
 * This class uses AES (Advanced Encryption Standard) for encryption and decryption.
 */
@Slf4j
@Component
class PasswordEncryptionDecryptionTool {

    private static final String ALGORITHM = 'AES'
    private static final int KEY_SIZE = 128
    private static final String TRANSFORMATION = 'AES'
    private static final String CIPHER_INSTANCE = 'AES/ECB/PKCS5Padding'

    /**
     * Encrypts a password using AES encryption.
     *
     * @param password The password to encrypt.
     * @return The encrypted password in Base64 encoded String.
     * @throws Exception If encryption fails.
     */
    String encryptPassword(String password) throws Exception {
        if (!password) {
            throw new IllegalArgumentException('Password cannot be empty.')
        }

        byte[] keyBytes = generateKey().getEncoded()
        byte[] encryptedBytes = encrypt(password.bytes, keyBytes)

        return Base64.getEncoder().encodeToString(encryptedBytes)
    }

    /**
     * Decrypts a password using AES decryption.
     *
     * @param encryptedPassword The encrypted password to decrypt.
     * @return The decrypted password.
     * @throws Exception If decryption fails.
     */
    String decryptPassword(String encryptedPassword) throws Exception {
        if (!encryptedPassword) {
            throw new IllegalArgumentException('Encrypted password cannot be empty.')
        }

        byte[] keyBytes = generateKey().getEncoded()
        byte[] decryptedBytes = decrypt(Base64.getDecoder().decode(encryptedPassword), keyBytes)

        return new String(decryptedBytes)
    }

    /**
     * Generates a new AES key.
     *
     * @return The generated AES key.
     */
    private SecretKey generateKey() {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM)
        keyGenerator.init(KEY_SIZE, SecureRandom.getInstanceStrong())
        return keyGenerator.generateKey()
    }

    /**
     * Encrypts data using the given key.
     *
     * @param data The data to encrypt.
     * @param keyBytes The key bytes.
     * @return The encrypted data.
     * @throws Exception If encryption fails.
     */
    private byte[] encrypt(byte[] data, byte[] keyBytes) throws Exception {
        SecretKeySpec key = new SecretKeySpec(keyBytes, ALGORITHM)
        Cipher cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, key)
        return cipher.doFinal(data)
    }

    /**
     * Decrypts data using the given key.
     *
     * @param data The data to decrypt.
     * @param keyBytes The key bytes.
     * @return The decrypted data.
     * @throws Exception If decryption fails.
     */
    private byte[] decrypt(byte[] data, byte[] keyBytes) throws Exception {
        SecretKeySpec key = new SecretKeySpec(keyBytes, ALGORITHM)
        Cipher cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.DECRYPT_MODE, key)
        return cipher.doFinal(data)
    }
}
