package br.com.danilo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UtilJson {
    public static <T> T parseJsonToObject(String body, Class<T> responseType) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(body, responseType);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
    
    public static <T> T readValue(String content, TypeReference<T> valueTypeRef) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(content, valueTypeRef);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
