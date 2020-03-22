package com.copy;

import com.copy.interceptor.CallTimeLogInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * mvc配置类
 *
 * @author zhongyong
 * @date 20181119
 * @since v1.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public CallTimeLogInterceptor callTimeLogInterceptor() {
        return new CallTimeLogInterceptor();
    }

    @Override
    public void  addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(callTimeLogInterceptor()).excludePathPatterns("/error");
    }

}
