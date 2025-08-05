// 代码生成时间: 2025-08-05 16:47:30
import grails.validation.Validateable

// 表单数据验证器类
class FormValidator implements Validateable {
    // 定义表单字段
    String name
    String email
    String password

    // 静态约束定义
    static constraints = {
        name(blank: false, matches: /^[a-zA-Z\s]+$/) // 非空且只包含字母和空格
        email(blank: false, email: true) // 非空且为有效邮箱
        password(blank: false, minSize: 8) // 非空且最少8位
    }

    // 验证表单的方法
    static boolean validateForm(Map formData) {
        // 创建一个FormValidator实例
        FormValidator validator = new FormValidator()
        // 设置表单数据
        validator.name = formData.name
        validator.email = formData.email
        validator.password = formData.password

        // 验证表单
        if (validator.validate()) {
            return true
        } else {
            // 获取错误信息并打印（实际项目中可能需要返回错误信息给前端）
            validator.errors.allErrors.each { log.error(it) }
            return false
        }
    }
}
