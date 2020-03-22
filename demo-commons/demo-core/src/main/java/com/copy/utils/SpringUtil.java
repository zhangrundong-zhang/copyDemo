package com.copy.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;

/**
 * spring获取bean工具类
 *
 * @author zhongyong
 * @date 20180612
 * @since v1.0
 */
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    public <T> T getBean(Class<T> cla) {
        return applicationContext.getBean(cla);
    }

    public <T> T getBean(String name, Class<T> cal) {
        return applicationContext.getBean(name, cal);
    }

    public String getProperty(String key) {
        return applicationContext.getBean(Environment.class).getProperty(key);
    }
}
