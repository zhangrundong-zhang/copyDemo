package com.copy.utils;

import com.copy.exception.AppException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.TimeZone;


/**
 * json工具类, 基于jackson. fastjson在特定情况下的序列化/反序列化有问题 <br/>
 *
 * @author zhongyong
 * @date 20180621
 * @since v1.0
 */
public class JsonUtils {

    /**
     * 工具类构造方法私有，禁止实例化
     * 直接调用该类的static方法
     */
    private JsonUtils() {
    }

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        OBJECT_MAPPER.registerModule(getJavaTimeModule());
    }

    public static JavaTimeModule getJavaTimeModule() {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        // 日期序列化格式
        javaTimeModule.addSerializer(LocalDateTime.class,
                new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DateUtils.DEFAULT_DATE_TIME_FORMAT)));
        javaTimeModule.addSerializer(LocalDate.class,
                new LocalDateSerializer(DateTimeFormatter.ofPattern(DateUtils.DEFAULT_DATE_FORMAT)));
        javaTimeModule.addSerializer(LocalTime.class,
                new LocalTimeSerializer(DateTimeFormatter.ofPattern(DateUtils.DEFAULT_TIME_FORMAT)));

        // 日期反序列化格式
        javaTimeModule.addDeserializer(LocalDateTime.class,
                new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DateUtils.DEFAULT_DATE_TIME_FORMAT)));
        javaTimeModule.addDeserializer(LocalDate.class,
                new LocalDateDeserializer(DateTimeFormatter.ofPattern(DateUtils.DEFAULT_DATE_FORMAT)));
        javaTimeModule.addDeserializer(LocalTime.class,
                new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DateUtils.DEFAULT_TIME_FORMAT)));
        return javaTimeModule;
    }

    /**
     * 使用泛型方法，把json字符串转换为相应的JavaBean对象。 <br/>
     * (1)转换为普通parse(json,User.class) <br/>
     * (2)转换为List,如 List(User) ,将第二个参数传递为User[].class. <br/>
     * 然后使用Arrays.asList();方法把得到的数组转换为特定类型的List
     *
     * @param jsonStr
     * @param valueType
     * @return
     */
    public static <T> T parseJson(String jsonStr, Class<T> valueType) {
        try {
            return OBJECT_MAPPER.readValue(jsonStr, valueType);
        } catch (Exception e) {
            throw new AppException(e);
        }
    }

    /**
     * 处理复杂泛型
     *
     * @param jsonStr
     * @param typeReference
     * @param <T>
     * @return
     */
    public static <T> T parseJson(String jsonStr, TypeReference typeReference) {
        try {
            return OBJECT_MAPPER.readValue(jsonStr, typeReference);
        } catch (Exception e) {
            throw new AppException(e);
        }
    }

    /**
     * Map 转obj
     *
     * @param map
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T parseObject(Map map, Class<T> valueType) {
        try {
            return OBJECT_MAPPER.convertValue(map, valueType);
        } catch (Exception e) {
            throw new AppException(e);
        }
    }

    /**
     * 泛型支持多级节点转换为相应JavaBean
     *
     * @param jsonStr
     * @param parentClass
     * @param elementClasses
     * @param <T>
     * @return
     */
    public static <T> T parseJson(String jsonStr, Class<?> parentClass, Class<?>... elementClasses) {
        JavaType javaType = getCollectionType(parentClass, elementClasses);
        try {
            return OBJECT_MAPPER.readValue(jsonStr, javaType);
        } catch (IOException e) {
            throw new AppException(e);
        }
    }

    /**
     * 把JavaBean转换为json字符串
     *
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (Exception e) {
            throw new AppException(e);
        }
    }

    public static <T> List<T> parseList(String jsonStr, Class<T> c) {
        JavaType javaType = getCollectionType(ArrayList.class, c);
        try {
            return OBJECT_MAPPER.readValue(jsonStr, javaType);
        } catch (IOException e) {
            throw new AppException(e);
        }
    }

    private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return OBJECT_MAPPER.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }


    /**
     * 读取json文件
     *
     * @param file json文件
     * @return 返回json字符串
     * @author zhongyong
     * @date 20180726
     * @since v1.0
     */
    public static String readJsonFile(File file) {
        StringBuilder buffer = new StringBuilder();
        try (Scanner scanner = new Scanner(file, "utf-8")) {
            while (scanner.hasNextLine()) {
                buffer.append(scanner.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    public static void writerJsonToFile(File file, String content) {
        try (FileWriter fwriter = new FileWriter(file)) {
            // true表示不覆盖原来的内容，而是加到文件的后面。若要覆盖原来的内容，直接省略这个参数就好
            fwriter.write(content);
            fwriter.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setLocale(Locale.CHINA);
        objectMapper.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
        // 忽略未知字段
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(JsonUtils.getJavaTimeModule()).registerModule(new ParameterNamesModule());
        return objectMapper;
    }


}
