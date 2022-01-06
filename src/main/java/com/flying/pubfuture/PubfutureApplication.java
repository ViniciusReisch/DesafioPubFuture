package com.flying.pubfuture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.flying.controller"})
public class PubfutureApplication {

	public static void main(String[] args) {
		SpringApplication.run(PubfutureApplication.class, args);
	}
}