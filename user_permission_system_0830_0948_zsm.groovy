// 代码生成时间: 2025-08-30 09:48:33
package com.example.security

// 引入Groovy和Grails框架的相关类
import grails.transaction.Transactional
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

// 用户权限管理服务
@Transactional
class PermissionService {
    // 检查用户是否具有特定权限
    boolean hasAuthority(String authority) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication()
            if (authentication != null && authentication.getAuthorities() != null) {
                for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
                    if (grantedAuthority.getAuthority().equals(authority)) {
                        return true
                    }
                }
            }
        } catch (Exception e) {
            // 处理异常
            log.error('Error checking user authority', e)
        }
        return false
    }

    // 检查用户是否属于特定角色
    boolean hasRole(String role) {
        return hasAuthority('ROLE_' + role.toUpperCase())
    }

    // 获取当前登录用户的信息
    UserDetails getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication()
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return (UserDetails) authentication.getPrincipal()
        }
        return null
    }
}

// 权限检查的拦截器
class PermissionInterceptor {
    PermissionService permissionService

    // 在执行控制器操作之前调用
    boolean before() {
        true
    }

    // 在执行控制器操作之后调用
    boolean after() {
        true
    }

    // 在控制器操作执行失败时调用
    def afterView() {
        def permission = request.getAttribute("permission")
        if (!permissionService.hasAuthority(permission)) {
            throw new AccessDeniedException("Insufficient permissions")
        }
    }
}

// 控制器示例，使用权限检查
class SecureController {
    def permissionService

    def index() {
        // 检查用户是否具有'ROLE_ADMIN'权限
        if (!permissionService.hasRole('ADMIN')) {
            throw new AccessDeniedException("Access Denied")
        }
        render "User has ADMIN privileges"
    }
}