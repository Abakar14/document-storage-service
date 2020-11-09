package com.bytmasoft.sms.spring.config;

import java.io.IOException;
import java.io.StringWriter;
import java.net.http.HttpClient;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.web.reactive.function.client.WebClient;

import com.bytmasoft.sms.exception.ExceptionWriter;
import com.bytmasoft.sms.view.StageManager;

import javafx.stage.Modality;
import javafx.stage.Stage;

@Configuration
public class AppJavaConfig {
	
	@Autowired
	SpringFXMLLoader springFXMLLoader;
	
	@Bean
	@ConditionalOnMissingBean
	HttpClient httpClient() {
//		HttpClient.newBuilder().version(Version.HTTP_2).build(); 
		return HttpClient.newBuilder().build();
	}	
	
	@Bean
	@Scope
	public ExceptionWriter exceptionWriter() {
		
		return new ExceptionWriter(new StringWriter());
	}

	@Bean
	public ResourceBundle resourceBundle() {
		return ResourceBundle.getBundle("bundle");
	}
	
	@Bean
	@Lazy(value = true) //Stage only created after spring context bootstarp
	public StageManager stageManager(Stage stage) throws IOException{
		return new StageManager(springFXMLLoader, stage);
	}
	
	
}
