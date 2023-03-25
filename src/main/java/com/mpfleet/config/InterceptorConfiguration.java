package com.mpfleet.config;

import com.mpfleet.interceptor.GeneralPurposeInterceptor;
import com.mpfleet.interceptor.TitleInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GeneralPurposeInterceptor());
        registry.addInterceptor(new TitleInterceptor());
    }
}
