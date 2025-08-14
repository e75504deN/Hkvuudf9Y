// 代码生成时间: 2025-08-14 08:31:28
package com.example.security

import grails.transaction.Transactional
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.beans.factory.annotation.Autowired

@Transactional
class UserAuthenticationService {

    @Autowired
    AuthenticationManager authenticationManager

    @Autowired
    PasswordEncoder passwordEncoder

    // 用户登录
    boolean authenticate(String username, String password) {
        // 将密码进行编码
        String encodedPassword = passwordEncoder.encode(password)
        // 创建认证令牌
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, encodedPassword)
        // 进行认证
        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken)
            SecurityContextHolder.getContext().setAuthentication(authentication)
            return true
        } catch (Exception e) {
            // 认证失败处理
            return false
        }
    }

    // 用户注册
    boolean register(String username, String password) {
        // 此处简化处理，实际应用中需要创建用户对象并保存到数据库
        try {
            String encodedPassword = passwordEncoder.encode(password)
            // 假设有一个userService来处理用户数据的保存
            // userService.saveUser(new User(username: username, password: encodedPassword))
            return true
        } catch (Exception e) {
            return false
        }
    }

    // 获取当前认证用户
    String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication()?.name
    }
}
