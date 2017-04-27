package com.sysmlvc.config;

/**
 * Created by Jason Han on 6/10/16.
 */

import org.neo4j.ogm.session.SessionFactory;
import org.neo4j.ogm.session.Session;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableScheduling
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.sysmlvc.services"})
@Configuration
@EnableNeo4jRepositories(basePackages = "com.sysmlvc.repositories")
@EnableTransactionManagement
public class Neo4jConfiguration {

    @Bean
    public SessionFactory sessionFactory() {
        return new SessionFactory("com.sysmlvc.domains", "BOOT-INF.classes.com.sysmlvc.domains");
    }

    @Bean
    public Neo4jTransactionManager transactionManager() {
        return new Neo4jTransactionManager(sessionFactory());
    }
}