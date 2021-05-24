package com.bytmasoft.sms.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.annotation.PostConstruct;

import org.checkerframework.checker.units.qual.m;
import org.checkerframework.checker.units.qual.mol;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

public class JSONUtils {

	private static ObjectMapper mapper;
	private static LocalDateTimeDeserializer localDateTimeDeserializer = new LocalDateTimeDeserializer(
			DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
	
	private static JavaTimeModule module = new JavaTimeModule();

	static public <T> List<T> convertFromJsonToList(String json, TypeReference<List<T>> var)
			throws JsonMappingException, JsonProcessingException {
		module.addDeserializer(LocalDateTime.class, localDateTimeDeserializer);
		
		mapper = Jackson2ObjectMapperBuilder.json()
				.modules(module)
				.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
				.build();


		
		return mapper.readValue(json, var);
	}

	static public <T> T convertFromJsonToObject(String json, Class<T> var)
			throws JsonMappingException, JsonProcessingException {
		mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());

		return mapper.readValue(json, var);
	}

	public static String convertFromObjectToJson(Object obj) throws JsonProcessingException {
		mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		return mapper.writeValueAsString(obj);
	}

}
