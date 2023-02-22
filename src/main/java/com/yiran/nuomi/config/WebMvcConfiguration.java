package com.yiran.nuomi.config;

import com.yiran.nuomi.controller.Interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 注册我的 拦截器
 *
 * @author Super-Zhang
 * @date 2021-09-18 8:34
 */
@Configuration// 配置类的注解。
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor)
                .addPathPatterns("/page/")
                .excludePathPatterns("/", "/static/", "/user/login", "/css/**", "/js/**", "/img/**")
        ;
    }

}
