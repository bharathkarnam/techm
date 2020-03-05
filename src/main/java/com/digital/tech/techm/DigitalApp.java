package com.digital.tech.techm;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@EnableAutoConfiguration
@EnableCircuitBreaker
@SpringBootApplication
public class DigitalApp {
	public static void main(String[] args) {
		new SpringApplicationBuilder(DigitalApp.class).registerShutdownHook(true).run(args);
	}
	
	
}
