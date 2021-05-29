/**
 * 
 */
package com.bytmasoft.dss.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Mahamat Abakar
 * created on 13.11.2020 at 23:05:45
 */
@Configuration
public class AppConfig {

	@Bean
	@LoadBalanced
	public WebClient webClientBuilder(){
		return WebClient.builder().build();
	}
	
}
