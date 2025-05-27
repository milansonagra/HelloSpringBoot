package com.example.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class ConfigDataSource {

    @Value("${DB_URL}")
    private String url;

    @Value("${DB_NAME:postgres}")
    private String dbName;

    @Value("${DB_USERNAME}")
    private String username;

    @Value("${DB_PASSWORD}")
    private String password;

    @Value("${DB_DRIVER_CLASSNAME}")
    private String driverClassName;

    @Bean
    public DataSource customDataSource() {
        return DataSourceBuilder.create()
                .url(url + dbName)
                .username(username)
                .password(password)
                .driverClassName(driverClassName)
                .build();
    }

    @Bean
    public JdbcTemplate jdbc(DataSource customDataSource) {
        return new JdbcTemplate(customDataSource);
    }
}
