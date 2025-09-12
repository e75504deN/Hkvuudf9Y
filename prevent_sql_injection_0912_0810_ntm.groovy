// 代码生成时间: 2025-09-12 08:10:53
import groovy.sql.Sql
import groovy.sql.GroovyRowResult
import org.springframework.jdbc.datasource.DriverManagerDataSource
import javax.sql.DataSource
import groovy.json.JsonSlurper
import groovy.json.JsonOutput

/**
 * Database Configuration Class
 * This class will hold the database configuration and provide a DataSource
 */
class DatabaseConfig {
    private static final String URL = "jdbc:mysql://localhost:3306/mydatabase"
    private static final String USERNAME = "root"
    private static final String PASSWORD = "password"
    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver"

    DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource()
        dataSource.setDriverClassName(DRIVER_CLASS_NAME)
        dataSource.setUrl(URL)
        dataSource.setUsername(USERNAME)
        dataSource.setPassword(PASSWORD)
        return dataSource
    }
}

/**
 * Data Access Object for User
 * This class will handle the data access operations related to the User entity
 */
class UserDao {
    private DataSource dataSource

    UserDao(DataSource dataSource) {
        this.dataSource = dataSource
    }

    /**
     * Find a user by username
     * @param username The username to search for
     * @return User data if found, null otherwise
     */
    User findUserByUsername(String username) {
        Sql sql = new Sql(dataSource)
        sql.withStatement { stmt ->
            String query = "SELECT * FROM users WHERE username = ?"
            stmt.setString(1, username)
            return sql.firstRow(query)
        }
    }

    /**
     * Add a new user
     * @param username The username of the new user
     * @param password The password of the new user
     * @return The newly created user
     */
    User addUser(String username, String password) {
        Sql sql = new Sql(dataSource)
        sql.withTransaction { transaction ->
            String insertQuery = "INSERT INTO users (username, password) VALUES (?, ?)"
            sql.executeInsert(insertQuery, [username, password])
            return findUserByUsername(username)
        }
    }
}

/**
 * User Entity
 * This class represents a User entity with properties id, username, and password
 */
class User {
    Integer id
    String username
    String password
}

// Example usage
def dataSource = new DatabaseConfig().dataSource()
def userDao = new UserDao(dataSource)

try {
    def user = userDao.findUserByUsername('admin')
    if (user) {
        println "User found: ${JsonOutput.toJson(user)}"
    } else {
        println "User not found"
    }

    def newUser = userDao.addUser('new_user', 'new_password')
    if (newUser) {
        println "New user created: ${JsonOutput.toJson(newUser)}"
    } else {
        println "Failed to create new user"
    }
} catch (Exception e) {
    println "An error occurred: ${e.message}"
}