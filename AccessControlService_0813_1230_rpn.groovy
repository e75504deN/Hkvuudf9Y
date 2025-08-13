// 代码生成时间: 2025-08-13 12:30:36
package com.example.security

import grails.transaction.Transactional

// Service class that handles access control logic
@Transactional
class AccessControlService {

    // Method to check if a user has permission to perform an action
    Boolean hasPermission(User user, String action) {
        // Assuming a Role and a Permission domain class
        // Check if the user is not null and the action is not null or empty
        if (!user || !action) {
            // Return false if input is invalid
            return false
        }

        try {
            // Retrieve user roles
            Set<Role> roles = user.roles

            // Check if the user has the required permission for the action
            return roles.any { Role role ->
                // Retrieve permissions for the role
                Set<Permission> permissions = role.permissions

                // Check if any of the permissions match the action
                permissions.any { Permission permission ->
                    permission.action == action
                }
            }
        } catch (Exception e) {
            // Log the exception and return false if an error occurs
            log.error('Error checking permission', e)
            return false
        }
    }

    // Method to add a new role to a user
    void addRoleToUser(User user, Role role) {
        if (!user || !role) {
            // Throw an exception if input is invalid
            throw new IllegalArgumentException('User or role cannot be null')
        }

        // Add the role to the user's roles
        user.addToRoles(role)
        user.save(flush: true)
    }

    // Method to remove a role from a user
    void removeRoleFromUser(User user, Role role) {
        if (!user || !role) {
            // Throw an exception if input is invalid
            throw new IllegalArgumentException('User or role cannot be null')
        }

        // Remove the role from the user's roles
        user.removeFromRoles(role)
        user.save(flush: true)
    }
}

// Domain class for User
class User {
    String username
    String password
    Set<Role> roles

    // Static constraints for the User class
    static constraints = {
        username blank: false, unique: true
        password blank: false
    }
}

// Domain class for Role
class Role {
    String authority
    Set<Permission> permissions

    // Static constraints for the Role class
    static constraints = {
        authority blank: false, unique: true
    }
}

// Domain class for Permission
class Permission {
    String action

    // Static constraints for the Permission class
    static constraints = {
        action blank: false, unique: true
    }
}
