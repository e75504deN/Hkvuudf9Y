// 代码生成时间: 2025-08-24 10:45:53
package com.example.themeswitcher

import grails.transaction.Transactional

// 主题切换服务
@Transactional
class ThemeSwitcherService {

    // 定义可用主题
    private static final List<String> AVAILABLE_THEMES = ['light', 'dark', 'colorful']

    // 保存主题到用户偏好设置
    void switchTheme(String themeName) {
        // 检查主题是否存在
        if (!AVAILABLE_THEMES.contains(themeName)) {
            throw new IllegalArgumentException("Theme '$themeName' does not exist.")
        }

        // 保存主题到用户偏好设置
        // 这里假设有一个UserService来处理用户的偏好设置
        UserService userService = new UserService()
        userService.setUserPreference('theme', themeName)
    }

    // 获取当前主题
    String getCurrentTheme() {
        // 这里假设有一个UserService来获取用户的偏好设置
        UserService userService = new UserService()
        return userService.getUserPreference('theme')
    }
}

// 用户服务类
class UserService {

    // 获取用户的偏好设置
    String getUserPreference(String key) {
        // 这里应该有一个数据库查询来获取用户的偏好设置
        // 为了示例，我们返回默认主题
        return 'light'
    }

    // 设置用户的偏好设置
    void setUserPreference(String key, String value) {
        // 这里应该有一个数据库更新来保存用户的偏好设置
        // 为了示例，我们只是打印出操作
        println "Setting user preference for $key to $value"
    }
}
