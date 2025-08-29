// 代码生成时间: 2025-08-30 02:03:37
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

// Define the UserRole enum to represent different user roles
enum UserRole {
    ADMIN, USER, GUEST
}

// Define the Authority domain class
class Authority {
    String name
    static constraints = {
        name blank: false, unique: true
    }
}

// Define the User domain class
class User {
    String username
    String password
    UserRole role
    static constraints = {
        username blank: false, unique: true
        password blank: false
        role nullable: false
    }
}

// Define the UserPermissionService service
@Transactional
class UserPermissionService {
    // Method to create a new user
    def createUser(String username, String password, UserRole role) {
        User user = new User(username: username, password: password, role: role)
        if (!user.validate()) {
            throw new RuntimeException('User validation failed')
        }
        user.save(flush: true)
        return user
    }

    // Method to assign role to a user
    def assignRole(String username, UserRole role) {
        User user = User.findByUsername(username)
        if (!user) {
            throw new RuntimeException('User not found')
        }
        user.role = role
        user.save(flush: true)
        return user
    }

    // Method to check user's role
    @Secured('ROLE_ADMIN')
    def hasRole(String username, UserRole role) {
        User user = User.findByUsername(username)
        if (!user) {
            throw new RuntimeException('User not found')
        }
        return user.role == role
    }
}

// Define the UserController controller
class UserController {
    def userPermissionService

    // Endpoint to create a new user
    def createUser() {
        String username = params.username
        String password = params.password
        UserRole role = UserRole.valueOf(params.role as String)
        try {
            User user = userPermissionService.createUser(username, password, role)
            render([message: 'User created successfully', user: user] as JSON)
        } catch (RuntimeException e) {
            render([error: e.message] as JSON)
        }
    }

    // Endpoint to assign role to a user
    def assignRole() {
        String username = params.username
        UserRole role = UserRole.valueOf(params.role as String)
        try {
            User user = userPermissionService.assignRole(username, role)
            render([message: 'Role assigned successfully', user: user] as JSON)
        } catch (RuntimeException e) {
            render([error: e.message] as JSON)
        }
    }

    // Endpoint to check if a user has a specific role
    def hasRole() {
        String username = params.username
        UserRole role = UserRole.valueOf(params.role as String)
        try {
            boolean hasRole = userPermissionService.hasRole(username, role)
            render([hasRole: hasRole] as JSON)
        } catch (RuntimeException e)
        {
            render([error: e.message] as JSON)
        }
    }
}