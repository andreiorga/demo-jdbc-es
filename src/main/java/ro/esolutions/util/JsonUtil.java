package ro.esolutions.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import org.springframework.util.StringUtils;

import java.util.Map;

public class JsonUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().setDefaultPropertyInclusion(Include.NON_ABSENT)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .registerModule(new JavaTimeModule())
            .setVisibility(PropertyAccessor.FIELD, Visibility.ANY)
            .setVisibility(PropertyAccessor.CREATOR, Visibility.NONE)
            .setVisibility(PropertyAccessor.GETTER, Visibility.NONE)
            .setVisibility(PropertyAccessor.SETTER, Visibility.NONE)
            .setVisibility(PropertyAccessor.IS_GETTER, Visibility.NONE);

    private static final ObjectWriter FORMATTED_OBJECT_WRITER = new ObjectMapper().setDefaultPropertyInclusion(Include.NON_ABSENT)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .registerModule(new JavaTimeModule())
            .setVisibility(PropertyAccessor.FIELD, Visibility.ANY)
            .setVisibility(PropertyAccessor.CREATOR, Visibility.NONE)
            .setVisibility(PropertyAccessor.GETTER, Visibility.NONE)
            .setVisibility(PropertyAccessor.SETTER, Visibility.NONE)
            .setVisibility(PropertyAccessor.IS_GETTER, Visibility.NONE)
            .writerWithDefaultPrettyPrinter();

    @SneakyThrows
    public static <T> T readResource(String classPathLocation, Class<T> clazz) {
        return OBJECT_MAPPER.readValue(JsonUtil.class.getClassLoader().getResourceAsStream(classPathLocation), clazz);
    }

    @SneakyThrows
    public static <T> T readValue(String json, Class<T> clazz) {
        return !StringUtils.hasLength(json) ? null : OBJECT_MAPPER.readValue(json, clazz);
    }

    @SneakyThrows
    public static <T> T readValue(String json, TypeReference<T> clazz) {
        return !StringUtils.hasLength(json) ? null : OBJECT_MAPPER.readValue(json, clazz);
    }

    @SneakyThrows
    public static <T> String writeValue(T value) {
        return value == null ? "" : OBJECT_MAPPER.writeValueAsString(value);
    }

    @SneakyThrows
    public static String formatJson(String json) {
        return FORMATTED_OBJECT_WRITER.writeValueAsString(readValue(json, Map.class));
    }

    @SuppressWarnings("unchecked")
    public static <T> T clone(T value) {
        return value == null ? null : (T) OBJECT_MAPPER.convertValue(value, value.getClass());
    }

    public static <T> T convertValue(Object value, Class<T> destinationClazz) {
        return value == null ? null : OBJECT_MAPPER.convertValue(value, destinationClazz);
    }
}
