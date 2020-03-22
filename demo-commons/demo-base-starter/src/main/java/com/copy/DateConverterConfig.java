package com.copy;

import com.copy.converter.LocalDateConvert;
import com.copy.converter.LocalDateTimeConvert;
import com.copy.converter.LocalTimeConvert;
import com.copy.utils.JsonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 时间json转换处理
 *
 * @author zhongyong
 * @date 20190527
 * @since v1.0
 */
@Configuration
public class DateConverterConfig {

    /**
     * 返回json的日期处理
     */
    @Bean
    public ObjectMapper objectMapper() {
        return JsonUtils.getObjectMapper();
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(ObjectMapper objectMapper) {
        return new MappingJackson2HttpMessageConverter(objectMapper);
    }

    /**
     * 接收前端日期的转换处理
     */
    @Bean
    public Converter<String, LocalDateTime> localDateTimeConvert() {
        return new LocalDateTimeConvert();
    }

    @Bean
    public Converter<String, LocalDate> localDateConvert() {
        return new LocalDateConvert();
    }

    @Bean
    public Converter<String, LocalTime> localTimeConvert() {
        return new LocalTimeConvert();
    }
}

