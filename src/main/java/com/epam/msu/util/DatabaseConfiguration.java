package com.epam.msu.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
public class DatabaseConfiguration {

    private final String dbUrl;
    private final String dbUsername;
    private final String dbPassword;

    public DatabaseConfiguration(Environment environment) {
        this.dbUrl = environment.getProperty("db.url");
        this.dbUsername = environment.getProperty("db.username");
        this.dbPassword = environment.getProperty("db.password");
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }
}
