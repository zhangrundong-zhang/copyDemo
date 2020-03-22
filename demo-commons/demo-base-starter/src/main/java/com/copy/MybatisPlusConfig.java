package com.copy;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.copy.mybatisplus.CustomMetaObjectHandler;
import com.copy.utils.SpringUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 公共自动加载类
 *
 * @author zhongyong
 * @date 20190613
 * @since v1.0
 */
@Configuration
public class MybatisPlusConfig {

    @Bean
    @ConditionalOnClass(MetaObjectHandler.class)
    public CustomMetaObjectHandler cpsMetaObjectHandler() {
        return new CustomMetaObjectHandler();
    }

    @Bean
    @ConditionalOnMissingBean(name = "springUtil")
    public SpringUtil springUtil() {
        return new SpringUtil();
    }

    @Bean
    @ConditionalOnClass(PaginationInterceptor.class)
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
