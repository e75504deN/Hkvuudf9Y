// 代码生成时间: 2025-08-21 06:13:33
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.userdetails.GrailsUser

// Define the UserPermissionManager service
@Transactional
class UserPermissionManager {

    // Inject the SpringSecurityService for authentication and authorization
    SpringSecurityService springSecurityService

    // List of available roles (can be extended as needed)
    private static final List<String> ROLES = ['ROLE_ADMIN', 'ROLE_USER', 'ROLE_GUEST']

    // Adds a new user with a specified role
    @Secured('ROLE_ADMIN')
    User createNewUser(String username, String password, String role) {
        if (!ROLES.contains(role)) {
            throw new IllegalArgumentException("Invalid role: ${role}")
        }

        // Create a new user instance with the provided username, password, and role
        GrailsUser user = new GrailsUser(
            username: username,
            password: password,
            enabled: true,
            accountExpired: false,
            accountLocked: false,
            passwordExpired: false
        )
        user.authorities = [new GrantedAuthorityImpl(authority: role)]

        // Save the new user to the database
        user.save(flush: true, failOnError: true)
        return user
    }

    // Grants a role to an existing user
    @Secured('ROLE_ADMIN')
    void grantRoleToUser(String username, String role) {
        if (!ROLES.contains(role)) {
            throw new IllegalArgumentException("Invalid role: ${role}")
        }

        GrailsUser user = GrailsUser.findByUsername(username)
        if (!user) {
            throw new IllegalArgumentException("User not found: ${username}")
        }

        user.authorities.add(new GrantedAuthorityImpl(authority: role))
        user.save(flush: true, failOnError: true)
    }

    // Revokes a role from an existing user
    @Secured('ROLE_ADMIN')
    void revokeRoleFromUser(String username, String role) {
        if (!ROLES.contains(role)) {
            throw new IllegalArgumentException("Invalid role: ${role}")
        }

        GrailsUser user = GrailsUser.findByUsername(username)
        if (!user) {
            throw new IllegalArgumentException("User not found: ${username}")
        }

        user.authorities.removeAll { it.authority == role }
        user.save(flush: true, failOnError: true)
    }

    // Checks if a user has a specific role
    @Secured('ROLE_ADMIN')
    boolean hasRole(String username, String role) {
        if (!ROLES.contains(role)) {
            throw new IllegalArgumentException("Invalid role: ${role}")
        }

        GrailsUser user = GrailsUser.findByUsername(username)
        if (!user) {
            throw new IllegalArgumentException("User not found: ${username}")
        }

        return user.authorities.any { it.authority == role }
    }
}
