// 代码生成时间: 2025-09-13 12:33:50
package com.example.security

import grails.transaction.Transactional
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

/**
 * 用户登录验证服务类
 */
@Transactional
class UserLoginService {

    // 注入认证管理器
    AuthenticationManager authenticationManager

    /**
     * 用户登录验证方法
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录成功返回用户信息，失败返回null
     */
    def login(String username, String password) {
        try {
            // 创建认证请求
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password)

            // 进行认证
            Authentication authentication = authenticationManager.authenticate(authenticationToken)

            // 如果认证通过，返回用户信息
            if (authentication.isAuthenticated()) {
                return authentication.principal
            } else {
                // 认证失败，返回null
                return null
            }
        } catch (Exception e) {
            // 出现异常，记录日志并返回null
            e.printStackTrace()
            return null
        }
    }

    /**
     * 登录验证失败处理方法
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录失败错误信息
     */
    def loginFail(String username, String password) {
        // 检查用户名和密码是否为空
        if (!username || !password) {
            return ['error': '用户名或密码不能为空']
        } else {
            // 尝试登录验证
            def userInfo = login(username, password)

            // 如果登录验证失败，返回错误信息
            if (!userInfo) {
                return ['error': '用户名或密码错误']
            } else {
                // 登录验证成功，设置认证信息
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userInfo, password, [] as List))
                return ['success': '登录成功']
            }
        }
    }
}