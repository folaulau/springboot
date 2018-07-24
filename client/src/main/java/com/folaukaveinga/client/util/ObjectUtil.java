package com.folaukaveinga.client.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ObjectUtil {
	   public static ObjectMapper getObjectMapper() {
	        ObjectMapper objectMapper = new ObjectMapper();
	        // Serialization
	        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
	        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

	        // Deserialization
	        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	        objectMapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);

	        return objectMapper;
	    }
}
