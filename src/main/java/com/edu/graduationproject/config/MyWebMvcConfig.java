package com.edu.graduationproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.edu.graduationproject.handler.VisitorLogger;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private VisitorLogger visitorLogger;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(visitorLogger)
                .addPathPatterns("/");
    }
}
