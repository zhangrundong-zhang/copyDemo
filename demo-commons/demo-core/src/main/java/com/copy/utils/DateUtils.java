package com.copy.utils;

import com.copy.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * java8日期工具类
 *
 * @author zhongyong
 * @date 20190321
 * @since v1.0
 */
@Slf4j
public class DateUtils {

    /**
     * 工具类构造方法私有，禁止实例化
     * 直接调用该类的static方法
     */
    private DateUtils() {
    }

    public static final String DATE_TIME_0 = "000000";
    public static final String DATE_TIME_05 = "yyyyMM";
    public static final String DATE_TIME_06 = "MMdd";
    public static final String DATE_TIME_07 = "MM-dd";

    /**
     * LocalDateTime默认日期时间格式 LocalDateTime
     */
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * DATE_TIME_FORMAT2 描述此常量
     */
    public static final String DEFAULT_DATE_TIME_FORMAT2 = "yyyyMMddHHmmss";

    /**
     * LocalDate默认日期格式
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    public static final String DEFAULT_DATE_FORMAT2 = "yyyyMMdd";

    /**
     * LocalTime默认时间格式
     */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";


    /**
     * 默认LocalDateTime时间格式: yyyy-MM-dd HH:mm:ss
     */
    private static final DateTimeFormatter DEFAULT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT);

    /**
     * 默认LocalDate时间格式: yyyy-MM-dd
     */
    private static final DateTimeFormatter DEFAULT_DATE_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);

    /**
     * 默认LocalTime时间格式: HH:mm:ss
     */
    private static final DateTimeFormatter DEFAULT_TIME_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT);


    /**
     * str转LocalDateTime
     *
     * @param localDateTimeStr 日期字符串，日期格式为yyyy-MM-dd HH:mm:ss
     * @since v1.0
     */
    public static LocalDateTime parseLocalDateTime(String localDateTimeStr) {
        return LocalDateTime.parse(localDateTimeStr, DEFAULT_DATETIME_FORMATTER);
    }

    /**
     * str转LocalDateTime,指定日期格式
     *
     * @param timeStr   日期字符串
     * @param formatter 指定日期格式
     * @since v1.0
     */
    public static LocalDateTime parseLocalDateTime(String timeStr, String formatter) {
        return LocalDateTime.parse(timeStr, DateTimeFormatter.ofPattern(formatter));
    }

    public static LocalDateTime parseLocalDateTime(Timestamp timestamp) {
        return LocalDateTime.ofInstant(timestamp.toInstant(), ZoneId.systemDefault());
    }


    /**
     * LocalDateTime转Str
     *
     * @param localDateTime 日期时间
     * @return 返回格式为yyyy-MM-dd HH:mm:ss字符串
     * @author zhongyong
     * @since v1.0
     */
    public static String formatLocalDateTime(LocalDateTime localDateTime) {
        return DEFAULT_DATETIME_FORMATTER.format(localDateTime);
    }

    /**
     * LocalDateTime转Str
     *
     * @param localDateTime 日期时间
     * @param formatter     指定的格式
     * @return 返回指定格式的字符串
     * @author zhongyong
     * @since v1.0
     */
    public static String formatLocalDateTime(LocalDateTime localDateTime, String formatter) {
        return DateTimeFormatter.ofPattern(formatter).format(localDateTime);
    }


    /**
     * str转LocalDate
     *
     * @param localDateStr 日期字符串，日期格式为yyyy-MM-dd
     * @author zhongyong
     * @since v1.0
     */
    public static LocalDate parseLocalDate(String localDateStr) {
        return LocalDate.parse(localDateStr, DEFAULT_DATE_FORMATTER);
    }

    /**
     * str转LocalDate,指定日期格式
     *
     * @param localDateStr 日期字符串
     * @param formatter    指定日期格式
     * @author zhongyong
     * @since v1.0
     */
    public static LocalDate parseLocalDate(String localDateStr, String formatter) {
        return LocalDate.parse(localDateStr, DateTimeFormatter.ofPattern(formatter));
    }


    /**
     * LocalDate转默认格式Str
     *
     * @param localDate 日期
     * @return 返回格式为yyyy-MM-dd字符串
     * @author zhongyong
     * @since v1.0
     */
    public static String formatLocalDate(LocalDate localDate) {
        return DEFAULT_DATE_FORMATTER.format(localDate);
    }

    /**
     * LocalDate转指定格式Str
     *
     * @param localDate 日期
     * @param formatter 指定的格式
     * @return 返回指定格式的字符串
     * @author zhongyong
     * @since v1.0
     */
    public static String formatLocalDate(LocalDate localDate, String formatter) {
        return DateTimeFormatter.ofPattern(formatter).format(localDate);
    }


    /**
     * str转LocalTime
     *
     * @param localTimeStr 日期字符串，日期格式为HH:mm:ss
     * @author zhongyong
     * @since v1.0
     */
    public static LocalTime parseLocalTime(String localTimeStr) {
        return LocalTime.parse(localTimeStr, DEFAULT_TIME_FORMATTER);
    }

    /**
     * str转LocalTime,指定日期格式
     *
     * @param localTimeStr 日期字符串
     * @param formatter    指定日期格式
     * @author zhongyong
     * @since v1.0
     */
    public static LocalTime parseLocalTime(String localTimeStr, String formatter) {
        return LocalTime.parse(localTimeStr, DateTimeFormatter.ofPattern(formatter));
    }


    /**
     * LocalTime转默认格式Str
     *
     * @param localTime 日期
     * @return 返回格式为HH:mm:ss字符串
     * @author zhongyong
     * @since v1.0
     */
    public static String formatLocalTime(LocalTime localTime) {
        return DEFAULT_TIME_FORMATTER.format(localTime);
    }

    /**
     * LocalDate转指定格式Str
     *
     * @param localTime 时间
     * @param formatter 指定的格式
     * @return 返回指定格式的字符串
     * @author zhongyong
     * @since v1.0
     */
    public static String formatLocalTime(LocalTime localTime, String formatter) {
        return DateTimeFormatter.ofPattern(formatter).format(localTime);
    }



    /**
     * 获取两个时间之间月数的差异
     *
     * @param observeTime  描述此参数
     * @param forecastTime 描述此参数
     * @return 返回 int 描述此返回参数
     * @author LeoRmAo
     * @date 20190715 14:05:34
     * @since v1.0
     */
    public static int getMonthBetween(LocalDateTime observeTime, LocalDateTime forecastTime) {
        //开始时间大于结束时间，抛出异常
        if (observeTime.isAfter(forecastTime)) {
            throw new AppException("开始时间，大于结束时间");
        }
        int startYear = observeTime.getYear();
        int endYear = forecastTime.getYear();
        int startMonth = observeTime.getMonthValue();
        int endMonth = forecastTime.getMonthValue();
        if (startYear == endYear) {
            return endMonth - startMonth;
        } else {
            return (endYear - startYear - 1) * 12 + (12 - startMonth) + endMonth;
        }
    }

    /**
     * 获取月日目录名，例如0101
     *
     * @param localDateTime 描述此参数
     * @return 返回 month day directory name
     * @author LeoRmAo
     * @date 20181115 10:57:28
     * @since v1.0
     */
    public static String getMonthDayDirectoryName(LocalDateTime localDateTime) {
        return monthDaySupplementZeroLessThanTen(localDateTime.getMonth().getValue()) +
                monthDaySupplementZeroLessThanTen(localDateTime.getDayOfMonth());
    }

    /**
     * 对小于10的数字进行补0，主要用于月和日的目录名获取
     *
     * @param number 描述此参数
     * @return 返回 string 描述此返回参数
     * @author LeoRmAo
     * @date 20181115 10:56:26
     * @since v1.0
     */
    public static String monthDaySupplementZeroLessThanTen(int number) {
        return number < 10 ? "0" + number : String.valueOf(number);
    }

    /**
     * 传入的日期，返回MMdd字符串
     *
     * @author 周恒
     * @date 20181130 15:28:43
     * @since v1.0
     */
    public static String monthAndDaySupplementZero(LocalDateTime localDateTime) {
        return monthDaySupplementZeroLessThanTen(localDateTime.getMonthValue())
                + monthDaySupplementZeroLessThanTen(localDateTime.getDayOfMonth());
    }

    /**
     * localDateTime转换为date
     *
     * @param localDateTime 描述此参数
     * @return 返回 date 描述此返回参数
     * @author LeoRmAo
     * @date 20180920 11:23:40
     * @since v1.0
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalDate转换为Date
     *
     * @param localDate 描述此参数
     * @return 返回 date 描述此返回参数
     * @author sunMing
     * @date 20190726
     * @since v1.0
     */
    public static Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 将date转为localDateTime
     *
     * @param date 描述此参数
     * @return 返回 local date time 描述此返回参数
     * @author LeoRmAo
     * @date 20181115 10:47:52
     * @since v1.0
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime
                .ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static Date parseString(String dateStr, String format) {
        if (StringUtils.isEmpty(dateStr)) {
            log.warn("invalid data,dateStr={}", dateStr);
            return null;
        }
        if (StringUtils.isEmpty(format)) {
            log.warn("invalid format,format={}", format);
            return null;
        }
        DateFormat df = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = df.parse(dateStr);
            if (!dateStr.equals(df.format(date))) {
                date = null;
            }
        } catch (ParseException e) {
            log.error("fail to parse date", e);
        }
        return date;
    }

    /**
     * 将传入的时间转为周一
     *
     * @return 返回 local date time 描述此返回参数
     * @author LeoRmAo
     * @date 20181129 17:57:58
     * @since v1.0
     */
    public static LocalDateTime getMonday(LocalDateTime localDateTime) {
        int value = localDateTime.getDayOfWeek().getValue();
        return localDateTime.minusDays(value - 1);
    }

    /**
     * 检查是否为2月29日
     *
     * @param forecastTime 预报时间
     * @return 返回 boolean 描述此返回参数
     * @author LeoRmAo
     * @date 20190821 17:32:34
     * @since v1.0
     */
    public static boolean check229(LocalDateTime forecastTime) {
        return forecastTime.getMonthValue() == 2 && forecastTime.getDayOfMonth() == 29;
    }

    /**
     * 获取当月最后一天的日期字符串
     *
     * @author 周恒
     * @date 20190321 14:05:33
     * @since v1.0
     */
    public static String getMonthLastDayNumber(LocalDateTime localDateTime) {
        return monthDaySupplementZeroLessThanTen(localDateTime.plusMonths(1).withDayOfMonth(1).minusDays(1).getDayOfMonth());
    }

    /**
     * 开始时间小于或等于 结束时间
     *
     * @param start 描述此参数
     * @param end   描述此参数
     * @return 返回 boolean 描述此返回参数
     * @author 周恒
     * @date 20181105 11:00:15
     * @since v1.0
     */
    public static boolean lessThanOrEqualTime(LocalDateTime start, LocalDateTime end) {
        if (start.isEqual(end)) {
            return true;
        }
        return end.isAfter(start);
    }


}
