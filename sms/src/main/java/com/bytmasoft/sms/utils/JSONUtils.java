package com.bytmasoft.sms.utils;

import java.util.List;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JSONUtils {

	private static ObjectMapper mapper;
	
	
	
	static public <T> List<T> convertFromJsonToList(String json, TypeReference <List<T>> var) throws JsonMappingException, JsonProcessingException{
		mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		return mapper.readValue(json, var);
	}
	
	
	static public <T> T convertFromJsonToObject(String json, Class<T> var) throws JsonMappingException, JsonProcessingException {
		mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		
		return mapper.readValue(json, var);
	}
	
	
	public static String convertFromObjectToJson(Object obj) throws JsonProcessingException {
		mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		return  mapper.writeValueAsString(obj);
	}
	
}
