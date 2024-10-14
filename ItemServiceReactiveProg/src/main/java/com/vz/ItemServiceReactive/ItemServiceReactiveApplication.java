package com.vz.ItemServiceReactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.reactive.config.EnableWebFlux;
@EnableWebFlux
@EnableAutoConfiguration
@EnableR2dbcRepositories
@EnableDiscoveryClient
//@EnableWebSecurity
@SpringBootApplication
public class ItemServiceReactiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemServiceReactiveApplication.class, args);
	}

}
