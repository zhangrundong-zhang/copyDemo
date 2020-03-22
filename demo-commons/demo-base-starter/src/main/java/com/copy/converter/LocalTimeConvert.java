package com.copy.converter;
import com.copy.utils.DateUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.time.LocalTime;

/**
 * LocalTime日期转换器
 *
 * @author zhongyong
 * @date 20190321
 * @since v1.0
 */
public class LocalTimeConvert implements Converter<String, LocalTime> {

    @Override
    public LocalTime convert(String source) {
        LocalTime time = null;
        if (StringUtils.hasText(source)) {
            time = DateUtils.parseLocalTime(source);
        }
        return time;
    }
}
