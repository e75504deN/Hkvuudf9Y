// 代码生成时间: 2025-09-14 09:18:42
import grails.validation.Validateable
import org.grails.databinding.BindUsing
import grails.core.support.proxy.ProxyHandler

// 表单数据验证器类
class FormValidator implements Validateable {
    // 定义表单中的字段
    String username
    String email
    String password

    // 静态约束条件
    static constraints = {
        // 用户名必须非空，并且长度在6到20之间
        username(nullable: false, blank: false, size: 6..20)

        // 邮箱必须非空，并且验证邮箱格式
        email(nullable: false, blank: false, email: true)

        // 密码必须非空，并且长度在8到20之间
        password(nullable: false, blank: false, size: 8..20)
    }

    // 定义自定义验证方法
    boolean hasValidUsername() {
        // 用户名不能包含非法字符
        if (username && !username.matches('^[a-zA-Z0-9_]+$')) {
            this.errors.rejectValue('username', 'default.username.invalid', 'Invalid username')
            return false
        }
        return true
    }

    // 定义自定义验证方法
    boolean hasValidEmail() {
        // 邮箱必须包含@符号
        if (email && !email.contains('@')) {
            this.errors.rejectValue('email', 'default.email.invalid', 'Invalid email')
            return false
        }
        return true
    }

    // 定义自定义验证方法
    boolean hasValidPassword() {
        // 密码必须包含大小写字母和数字
        if (password && (!password.matches('^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).+$'))) {
            this.errors.rejectValue('password', 'default.password.invalid', 'Password must contain uppercase, lowercase, and digit')
            return false
        }
        return true
    }
}

// 测试方法
class FormValidatorTest {
    static void main(String[] args) {
        // 创建表单验证器实例
        FormValidator formValidator = new FormValidator(username: 'TestUser', email: 'test@example.com', password: 'Secure123')

        // 验证表单数据
        if (formValidator.validate()) {
            println 'Form data is valid.'
        } else {
            // 打印验证错误信息
            formValidator.errors.allErrors.each { println it.defaultMessage }
        }
    }
}