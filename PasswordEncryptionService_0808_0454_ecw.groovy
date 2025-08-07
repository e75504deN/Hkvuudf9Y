// 代码生成时间: 2025-08-08 04:54:59
import groovy.util.logging.Slf4j
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Slf4j
class PasswordEncryptionService {

    /**
     * Encrypts the provided password using BCrypt algorithm.
     *
     * @param password The plain text password to be encrypted.
     * @return The encrypted password.
     */
    String encryptPassword(String password) {
        PasswordEncoder encoder = new BCryptPasswordEncoder()
        return encoder.encode(password)
    }

    /**
     * Decrypts the provided password using BCrypt algorithm.
     *
     * @param encryptedPassword The encrypted password to be decrypted.
     * @param rawPassword The plain text password to compare with.
     * @return True if the passwords match, false otherwise.
     */
    boolean decryptPassword(String encryptedPassword, String rawPassword) {
        PasswordEncoder encoder = new BCryptPasswordEncoder()
        try {
            return encoder.matches(rawPassword, encryptedPassword)
        } catch (Exception e) {
            log.error('Error decrypting password', e)
            throw new RuntimeException('Error decrypting password')
        }
    }

    /**
     * Validates the provided password against the encrypted password.
     *
     * @param encryptedPassword The encrypted password to validate against.
     * @param rawPassword The plain text password to validate.
     * @return True if the passwords match, false otherwise.
     */
    boolean validatePassword(String encryptedPassword, String rawPassword) {
        return decryptPassword(encryptedPassword, rawPassword)
    }
}
