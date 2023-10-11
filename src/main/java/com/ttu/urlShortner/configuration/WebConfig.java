package com.ttu.urlShortner.configuration;

import com.ttu.urlShortner.interceptor.CreateApiValidationInterceptor;
import com.ttu.urlShortner.interceptor.UpdateExpiryApiValidationInterceptor;
import com.ttu.urlShortner.interceptor.UpdateLongUrlApiValidationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private CreateApiValidationInterceptor createApiValidationInterceptor;

    @Autowired
    private UpdateLongUrlApiValidationInterceptor updateApiValidationInterceptor;

    @Autowired
    private UpdateExpiryApiValidationInterceptor updateExpiryApiValidationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(createApiValidationInterceptor).addPathPatterns("/longUrl");
        registry.addInterceptor(updateApiValidationInterceptor).addPathPatterns("/updateLongUrl");
        registry.addInterceptor(updateExpiryApiValidationInterceptor).addPathPatterns("/updateExpiry");
    }
}
