package com.vz.customerservicereactive;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.reactive.config.EnableWebFlux;

import io.r2dbc.spi.ConnectionFactory;
//import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
@EnableWebFlux
@EnableAutoConfiguration
@EnableR2dbcRepositories
@EnableDiscoveryClient
@EnableWebSecurity
@SpringBootApplication
// Exclude Spring MVC auto-configuration
//@EnableR2dbcRepositories
public class CustomerServiceReactiveApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceReactiveApplication.class, args);
    }
//    @Bean
//    ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
//
//      ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
//      initializer.setConnectionFactory(connectionFactory);
//      initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("data.sql")));
//
//      return initializer;
//    }
}