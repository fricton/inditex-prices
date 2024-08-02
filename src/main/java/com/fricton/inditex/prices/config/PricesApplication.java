package com.fricton.inditex.prices.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/*
 * Infrastructure beans are framework-dependent, using SpringMVC and SpringJPA.
 * The rest of the beans are created in the configuration package, domain and application packages are not framework-dependent
 */

@SpringBootApplication(scanBasePackages = { "com.fricton.inditex.prices.config",
		"com.fricton.inditex.prices.infrastructure" })
@EntityScan(basePackages = "com.fricton.inditex.prices.infrastructure.db.h2.entities")
@EnableJpaRepositories(basePackages = { "com.fricton.inditex.prices.infrastructure.db.h2.repositories" })
@EnableAutoConfiguration
@EnableConfigurationProperties
public class PricesApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(PricesApplication.class, args);
	}

}
