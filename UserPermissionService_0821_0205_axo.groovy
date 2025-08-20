// 代码生成时间: 2025-08-21 02:05:04
class UserPermissionService {

    //注入数据访问 objects for Role and Permission
    def roleService
    def permissionService

    /**<ol>
     * Assigns a permission to a role.
     *
     * @param role The role to assign the permission to.
     * @param permission The permission to assign.
     * @return true if the assignment was successful, false otherwise.
     * @throws IllegalArgumentException if the role or permission is null.
     */
    boolean assignPermissionToRole(Role role, Permission permission) {
        if (!role || !permission) {
            throw new IllegalArgumentException('Role or Permission cannot be null.')
        }
        role.addToPermissions(permission)
        roleService.save(role)
        return true
    }

    /**<ol>
     * Removes a permission from a role.
     *
     * @param role The role to remove the permission from.
     * @param permission The permission to remove.
     * @return true if the removal was successful, false otherwise.
     * @throws IllegalArgumentException if the role or permission is null.
     */
    boolean removePermissionFromRole(Role role, Permission permission) {
        if (!role || !permission) {
            throw new IllegalArgumentException('Role or Permission cannot be null.')
        }
        role.removeFromPermissions(permission)
        roleService.save(role)
        return true
    }

    /**<ol>
     * Assigns a role to a user.
     *
     * @param user The user to assign the role to.
     * @param role The role to assign.
     * @return true if the assignment was successful, false otherwise.
     * @throws IllegalArgumentException if the user or role is null.
     */
    boolean assignRoleToUser(User user, Role role) {
        if (!user || !role) {
            throw new IllegalArgumentException('User or Role cannot be null.')
        }
        user.addToRoles(role)
        user.save(flush: true)
        return true
    }

    /**<ol>
     * Removes a role from a user.
     *
     * @param user The user to remove the role from.
     * @param role The role to remove.
     * @return true if the removal was successful, false otherwise.
     * @throws IllegalArgumentException if the user or role is null.
     */
    boolean removeRoleFromUser(User user, Role role) {
        if (!user || !role) {
            throw new IllegalArgumentException('User or Role cannot be null.')
        }
        user.removeFromRoles(role)
        user.save(flush: true)
        return true
    }

    /**<ol>
     * Checks if a user has a specific permission.
     *
     * @param user The user to check.
     * @param permission The permission to check for.
     * @return true if the user has the permission, false otherwise.
     * @throws IllegalArgumentException if the user or permission is null.
     */
    boolean hasPermission(User user, Permission permission) {
        if (!user || !permission) {
            throw new IllegalArgumentException('User or Permission cannot be null.')
        }
        return user.authorities.find { it.authority == permission.name } != null
    }

    // Add more methods as needed for additional functionality

}

/**
 * Role.groovy
 *
 * Represents a role within the application.
 */
class Role {
    String authority
    // Associations
    Set<Permission> permissions
    Set<User> users
    // Standard persistence methods
    static hasMany = [permissions: Permission, users: User]
}

/**
 * Permission.groovy
 *
 * Represents a permission within the application.
 */
class Permission {
    String name
    // Associations
    Set<Role> roles
    // Standard persistence methods
    static hasMany = [roles: Role]
}

/**
 * User.groovy
 *
 * Represents a user within the application.
 */
class User {
    String username
    String password
    // Associations
    Set<Role> roles
    // Standard persistence methods
    static hasMany = [roles: Role]
}
