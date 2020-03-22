package com.copy.utils;

import com.copy.exception.AppException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.Map;

/**
 * 断言工具类
 *
 * @author zhongyong
 * @date 20190807
 * @since v1.0
 */
public class AssertUtils {

    /**
     * 工具类构造方法私有，禁止实例化
     * 直接调用该类的static方法
     */
    private AssertUtils() {
    }

    public static void state(boolean expression, String message) {
        if (expression) {
            throw new AppException(message);
        }
    }

    public static void notNull(@Nullable Object object, String message) {
        if (object == null) {
            throw new AppException(message);
        }
    }

    public static void notBlankStr(CharSequence cs, String message) {
        if (StringUtils.isBlank(cs)) {
            throw new AppException(message);
        }
    }

    public static void notEmptyCollections(@Nullable Collection<?> collection, String message) {
        if (collection == null || collection.isEmpty()) {
            throw new AppException(message);
        }
    }

    public static void notEmptyMap(@Nullable Map<?, ?> map, String message) {
        if (map == null || map.isEmpty()) {
            throw new AppException(message);
        }
    }
}
