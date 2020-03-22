package com.copy.converter;

import com.copy.utils.DateUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

/**
 * LocalDate日期转换器
 *
 * @author zhongyong
 * @date 20190321
 * @since v1.0
 */
public class LocalDateConvert implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String source) {
        LocalDate time = null;
        if (StringUtils.hasText(source)) {
            time = DateUtils.parseLocalDate(source);
        }
        return time;
    }
}
