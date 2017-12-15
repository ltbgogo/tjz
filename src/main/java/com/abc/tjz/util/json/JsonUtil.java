package com.abc.tjz.util.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author LiuTongbin
 * @date 2017/12/6 0006 22:59
 */
public class JsonUtil {

    public static ObjectMapper getObjectMapper() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        simpleModule.addSerializer(Double.class, new DoubleSerializer());
        simpleModule.addSerializer(double.class, new DoubleSerializer());
        simpleModule.addSerializer(new TextEnumSerializer());
        simpleModule.addSerializer(new DateSerializer(false, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        filterProvider.addFilter(JpaEntityPropertyFilter.ID, new JpaEntityPropertyFilter());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(simpleModule);
        objectMapper.registerModule(javaTimeModule);
        objectMapper.setAnnotationIntrospector(new JpaLazyIntrospector());
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.setFilterProvider(filterProvider);
        return objectMapper;
    }

    @SneakyThrows
    public static String toJson(Object value, boolean isPretty) {
        return (isPretty ? OM_PRETTY : OM_INLINE).writeValueAsString(value);
    }

    @SneakyThrows
    public static <T> T convert(Object fromValue, Class<T> toValueType) {
        return OM_INLINE.convertValue(fromValue, toValueType);
    }

    public static JsonNode valueToTree(Object fromValue) {
        return new ObjectMapper().valueToTree(fromValue);
    }

    private static final ObjectMapper OM_INLINE = getObjectMapper();
    private static final ObjectMapper OM_PRETTY = OM_INLINE.configure(SerializationFeature.INDENT_OUTPUT, true);
}
