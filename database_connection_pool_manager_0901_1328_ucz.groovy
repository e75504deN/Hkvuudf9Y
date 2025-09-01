// 代码生成时间: 2025-09-01 13:28:27
package com.example.db

import groovy.sql.Sql
import org.apache.commons.dbcp.BasicDataSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import javax.sql.DataSource
import org.springframework.transaction.PlatformTransactionManager

/**
 * Database Configuration for Grails application.
 */
@Configuration
class DatabaseConfig {

    /**
     * Configures the DataSource for the application.
     *
     * @return DataSource instance configured with Apache Commons DBCP.
     */
    @Bean
    DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource()
        dataSource.setDriverClassName('com.mysql.cj.jdbc.Driver')
        dataSource.setUrl('jdbc:mysql://localhost:3306/your_database')
        dataSource.setUsername('username')
        dataSource.setPassword('password')
        // Configure other properties as needed, such as connection pool settings
        dataSource.setMaxTotal(10)
        dataSource.setDefaultAutoCommit(true)
        return dataSource
    }

    /**
     * Configures the Transaction Manager for the application.
     *
     * @param dataSource The DataSource instance to use.
     * @return PlatformTransactionManager instance configured for the DataSource.
     */
    @Bean
    PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource)
    }
}

/**
 * Service class to manage database operations using the connection pool.
 */
class DatabaseService {

    private final Sql sql

    /**
     * Constructs a new DatabaseService instance with a Sql object.
     *
     * @param dataSource The DataSource to use for database operations.
     */
    DatabaseService(DataSource dataSource) {
        this.sql = new Sql(dataSource)
    }

    /**
     * Executes a query and returns a List of Map entries.
     *
     * @param query The SQL query to execute.
     * @return A list of Map entries representing the query results.
     */
    List<Map> executeQuery(String query) {
        try {
            return sql.rows(query)
        } catch (SQLException e) {
            // Handle exception, possibly log and rethrow or handle it gracefully
            throw new RuntimeException("Failed to execute query: $query", e)
        }
    }

    /**
     * Executes an update statement.
     *
     * @param query The SQL update statement to execute.
     * @return The number of rows affected by the update.
     */
    int executeUpdate(String query) {
        try {
            return sql.executeUpdate(query)
        } catch (SQLException e) {
            // Handle exception, possibly log and rethrow or handle it gracefully
            throw new RuntimeException("Failed to execute update: $query", e)
        }
    }

    /**
     * Closes the Sql connection.
     */
    void close() {
        sql.close()
    }
}