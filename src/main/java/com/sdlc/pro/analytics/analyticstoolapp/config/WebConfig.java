package com.sdlc.pro.analytics.analyticstoolapp.config;


import com.sdlc.pro.analytics.analyticstoolapp.intercepptor.AnalyticsInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    AnalyticsInterceptor analyticsInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(analyticsInterceptor).addPathPatterns("/**");
    }
}
