package com.gaoming.rtz.querydsl.config;

import com.querydsl.sql.MySQLTemplates;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.SQLTemplates;
import com.querydsl.sql.spring.SpringConnectionProvider;
import com.querydsl.sql.spring.SpringExceptionTranslator;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.inject.Provider;
import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @file: QueryDslConfig
 * @author: gaoming
 * @date: 2021/05/12
 * @version: 1.0
 * @description:
 **/
@Configuration
public class QueryDslConfig {
    @Bean
    public DataSource dataSource() {
        // implementation omitted
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/boke?useUnicode=true&characterEncoding=UTF-8");
        hikariDataSource.setUsername("root");
        hikariDataSource.setPassword("root");
        return hikariDataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public com.querydsl.sql.Configuration querydslConfiguration() {
        SQLTemplates templates = MySQLTemplates.builder().quote().build();
        com.querydsl.sql.Configuration configuration = new com.querydsl.sql.Configuration(templates);
        configuration.setExceptionTranslator(new SpringExceptionTranslator());
        return configuration;
    }

    @Bean(name = "sqlQueryFactory")
    public SQLQueryFactory queryFactory() {
        Provider<Connection> provider = new SpringConnectionProvider(dataSource());
        return new SQLQueryFactory(querydslConfiguration(), provider);
    }
}
