// 代码生成时间: 2025-09-01 00:27:33
import grails.transaction.Transactional

/**
 * 用户身份认证服务
 */
@Transactional
class AuthenticationService {

    /**
     * 用户身份验证
     * @param username 用户名
     * @param password 密码
     * @return Map 包含验证结果和消息
     */
    def authenticateUser(String username, String password) {
        def authResult = [:]

        try {
            // 查找用户
            def user = User.findByUsername(username)
            if (!user) {
                authResult.result = 'failure'
                authResult.message = 'User not found.'
                return authResult
            }

            // 验证密码
            if (user && user.password == password) {
                authResult.result = 'success'
                authResult.message = 'Authentication successful.'
            } else {
                authResult.result = 'failure'
                authResult.message = 'Invalid username or password.'
            }
        } catch (Exception e) {
            authResult.result = 'failure'
            authResult.message = 'An error occurred during authentication: ' + e.message
        }

        return authResult
    }
}

/**
 * 用户实体
 */
class User {
    String username
    String password

    static constraints = {
        username blank: false, unique: true
        password blank: false
    }
}
