package com.fastdeliveryservice.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Martina
 */
public final class  SerializerUtils {

    public static <T> String serializeToJson(Class<T> typeKey) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";

        try {
            jsonInString = mapper.writeValueAsString(typeKey);
        } catch (JsonProcessingException e) {
        }


        return jsonInString;
    }

    public static <T> String serializeToJson(List<Class<T>> orders) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";

        final Map<String, List<Class<T>>> dataMap = new HashMap<>();
        dataMap.put("orders", orders);

        try {
            jsonInString = mapper.writeValueAsString(dataMap);
        } catch (JsonProcessingException e) {
        }

        return jsonInString;
    }
}
