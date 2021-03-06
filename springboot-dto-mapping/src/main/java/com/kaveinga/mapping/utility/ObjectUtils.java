package com.kaveinga.mapping.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

public class ObjectUtils {
	public static ObjectMapper getObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new Jdk8Module());
		// Serialization
		// Serialization
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
		objectMapper.configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, true);
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
		objectMapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, true);
		//objectMapper.configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, false);
		// Deserialization
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
		objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES, false);

		objectMapper.setSerializationInclusion(Include.NON_NULL);

		// Date and Time Format
		objectMapper.setDateFormat(getSidecarDateForm());

		objectMapper.setSerializationInclusion(Include.NON_NULL);
		return objectMapper;
	}
	
	private static DateFormat getSidecarDateForm() {
		return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
	}
	
	public static <T> T map(Object object, Class<T> clazz) {
		try {
			return getObjectMapper().convertValue(object, clazz);
		} catch (Exception e) {
			System.out.println("ObjectUtil - printJson - JsonProcessingException - Msg: " + e.getLocalizedMessage());
			return null;
		}
	}
	
	public static String toJson(Object object) {
		try {
			return ObjectUtils.getObjectMapper().writeValueAsString(object);
		} catch (JsonProcessingException e) {
			System.out.println("JsonProcessingException, msg: "+e.getLocalizedMessage());
			return "{}";
		}
	}
	
	public static <T> T fromJsonString(String json, Class<T> clazz) {
		try {
			return ObjectUtils.getObjectMapper().readValue(json, clazz);
		} catch (Exception e) {
			System.out.println("JsonProcessingException, msg: "+e.getLocalizedMessage());
			return null;
		}
	}
}
