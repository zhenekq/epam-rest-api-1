package com.epam.msu.config;

import com.epam.msu.util.DatabaseConfiguration;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.epam.msu")
@EnableWebMvc
public class SpringConfig implements WebMvcConfigurer {
    private final ApplicationContext applicationContext;
    private final DatabaseConfiguration databaseConfiguration;

    @Autowired
    public SpringConfig(ApplicationContext applicationContext, DatabaseConfiguration databaseConfiguration) {
        this.applicationContext = applicationContext;
        this.databaseConfiguration = databaseConfiguration;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }



    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(databaseConfiguration.getDbUrl());
        dataSource.setUsername(databaseConfiguration.getDbUsername());
        dataSource.setPassword(databaseConfiguration.getDbPassword());
        dataSource.setMinIdle(databaseConfiguration.getMinIdle());
        dataSource.setMaxIdle(databaseConfiguration.getMaxIdle());
        dataSource.setMaxOpenPreparedStatements(databaseConfiguration.getMaxOpenPreparedStatements());
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}
