package com.flying.pubfuture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"test"})
@ComponentScan({"com.flying.controller"})
@EnableJpaRepositories("com.flying.repository")
@EntityScan("com.flying.model")
@EnableAutoConfiguration
public class PubfutureApplication {

	public static void main(String[] args) {
		SpringApplication.run(PubfutureApplication.class, args);
	}
}