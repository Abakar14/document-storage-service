package com.bytmasoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@EntityScan(basePackages = { "com.bytmasoft" })
@ComponentScan(basePackages = { "com.bytmasoft" })
@SpringBootApplication
@EnableEurekaClient
public class DocumentStorageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocumentStorageServiceApplication.class, args);
	}

}
