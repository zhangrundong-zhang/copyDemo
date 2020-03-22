package com.copy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meitq.base.converter.LocalDateConvert;
import com.meitq.base.converter.LocalDateTimeConvert;
import com.meitq.base.converter.LocalTimeConvert;
import com.meitq.util.JsonUtils;
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

