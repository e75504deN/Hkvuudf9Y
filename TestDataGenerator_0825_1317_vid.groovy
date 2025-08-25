// 代码生成时间: 2025-08-25 13:17:56
package com.example

import groovy.transform.CompileStatic
import org.springframework.stereotype.Service

/**
 * Service class for generating test data.
 */
@Service
@CompileStatic
class TestDataGenerator {

    /**
     * Generates a list of random user data.
     *
     * @param count The number of users to generate.
     * @return A list of user data.
     */
    List<Map<String, String>> generateUserTestData(int count) {
        List<Map<String, String>> users = []
        Random random = new Random()
        for (int i = 0; i < count; i++) {
            Map<String, String> userData = [
                'id': UUID.randomUUID().toString(),
                'name': 'User ' + (i + 1),
                'email': 'user' + (i + 1) + '@example.com',
                'age': Integer.toString(random.nextInt(60) + 18) // 18 to 78
            ]
            users << userData
        }
        return users
    }

    /**
     * Generates a list of random product data.
     *
     * @param count The number of products to generate.
     * @return A list of product data.
     */
    List<Map<String, String>> generateProductTestData(int count) {
        List<Map<String, String>> products = []
        Random random = new Random()
        for (int i = 0; i < count; i++) {
            Map<String, String> productData = [
                'id': UUID.randomUUID().toString(),
                'name': 'Product ' + (i + 1),
                'price': Float.toString(random.nextFloat() * 100),
                'category': Categories.values()[random.nextInt(Categories.values().length)].toString()
            ]
            products << productData
        }
        return products
    }

    private enum Categories {
        BOOKS, ELECTRONICS, HOME, FASHION, FOOD
    }
}