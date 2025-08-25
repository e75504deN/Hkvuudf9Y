// 代码生成时间: 2025-08-25 09:34:27
import groovy.transform.CompileStatic
import org.springframework.stereotype.Service

// 测试数据生成器服务
@CompileStatic
@Service
class TestDataGenerator {

    // 随机生成用户数据
    List<Map<String, String>> generateUserData(int count) {
        List<Map<String, String>> users = []
        for (int i = 0; i < count; i++) {
            Map<String, String> user = [
                'username': "user${i}",
                'password': "password${i}",
                'email': "user${i}@example.com"
            ]
            users.add(user)
        }
        return users
    }

    // 随机生成产品数据
    List<Map<String, Object>> generateProductData(int count) {
        List<Map<String, Object>> products = []
        for (int i = 0; i < count; i++) {
            Map<String, Object> product = [
                'id': i,
                'name': "Product ${i}",
                'price': Math.random() * 100,
                'inStock': Math.random() > 0.5
            ]
            products.add(product)
        }
        return products
    }

    // 生成测试数据并打印
    void generateAndPrintTestData(int userCount, int productCount) {
        try {
            List<Map<String, String>> users = generateUserData(userCount)
            List<Map<String, Object>> products = generateProductData(productCount)

            println "Generated Users: $users"
            println "Generated Products: $products"
        } catch (Exception e) {
            // 错误处理
            println "Error generating test data: ${e.message}"
        }
    }

    // 主方法用于测试
    static void main(String[] args) {
        TestDataGenerator generator = new TestDataGenerator()
        generator.generateAndPrintTestData(10, 10)
    }
}
