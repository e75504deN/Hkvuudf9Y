// 代码生成时间: 2025-08-12 11:54:14
package com.example

import groovy.sql.Sql
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.sql.DataSource

/**
 * Service class to demonstrate prevention of SQL injection using Grails framework.
 */
@Service
class SqlInjectionPreventionService {

    // Autowiring the DataSource to be used by JdbcTemplate
    @Autowired
    private DataSource dataSource

    // Using JdbcTemplate to execute queries safely
    private JdbcTemplate jdbcTemplate

    // Constructor to inject DataSource and initialize JdbcTemplate
    SqlInjectionPreventionService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource)
    }

    /**
     * Retrieves user details based on the provided username.
     *
     * @param username The username to search for.
     * @return A User object if found, null otherwise.
     */
    def getUserByUsername(String username) {
        try {
            // Using named parameters to prevent SQL injection
            MapSqlParameterSource params = new MapSqlParameterSource()
            params.addValue('username', username)

            // Defining the query with named parameters
            String sql = "SELECT id, username, email FROM users WHERE username = :username"

            // Executing the query using NamedParameterJdbcTemplate
            List<Map<String, Object>> results = new NamedParameterJdbcTemplate(jdbcTemplate).queryForList(sql, params)

            // If user exists, create and return a User object
            if (!results.isEmpty()) {
                return new User(id: results[0].id, username: results[0].username, email: results[0].email)
            }
        } catch (Exception e) {
            // Error handling
            e.printStackTrace()
        }
        return null
    }

    /**
     * Inner class representing a User.
     */
    static class User {
        Long id
        String username
        String email
    }
}
