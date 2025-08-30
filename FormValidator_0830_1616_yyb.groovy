// 代码生成时间: 2025-08-30 16:16:00
import grails.validation.Validateable
import grails.validation.ValidationException

// 表单数据验证器类
class CustomForm implements Validateable {
    String name
    String email
    String password
    int age

    // 验证规则
    static constraints = {
        name(blank: false, nullable: false, maxSize: 100)
        email(email: true, blank: false, nullable: false)
        password(blank: false, nullable: false, maxSize: 100)
        age(min: 18, max: 100, blank: false, nullable: false)
    }

    // 自定义验证方法
    public void validate() {
        super.validate()
        if (age < 18) {
            errors.rejectValue('age', 'default.age.min', ['18'] as Object[], 'Age must be at least 18.')
        }
    }
    
    // 用于返回验证错误消息的方法
    List<String> getErrorMessages() {
        def messages = []
        for (ObjectError error : this.errors.allErrors) {
            messages.add(error.defaultMessage)
        }
        return messages
    }
}

// 使用示例
try {
    CustomForm form = new CustomForm(name: 'John Doe', email: 'john.doe@example.com', password: 'password123', age: 17)
    form.validate()
    if (form.hasErrors()) {
        println 'Validation errors: ' + form.getErrorMessages()
    } else {
        println 'Form is valid!'
    }
} catch (ValidationException e) {
    println 'Validation failed: ' + e.message
}