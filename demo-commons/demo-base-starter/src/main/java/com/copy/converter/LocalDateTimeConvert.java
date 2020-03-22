package com.copy.converter;

import com.copy.utils.DateUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * LocalDateTime日期转换器
 *
 * @author zhongyong
 * @date 20190321
 * @since v1.0
 */
public class LocalDateTimeConvert implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String source) {
        LocalDateTime time = null;
        if (StringUtils.hasText(source)) {
            time = DateUtils.parseLocalDateTime(source);
        }
        return time;
    }
}
