package com.nhnacademy.edu.springboot.account.config;

import com.nhnacademy.edu.springboot.account.interceptor.GetAccountInterceptor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;

@Configuration
public class WebClientConfig implements WebMvcConfigurer {
    private final GetAccountInterceptor getAccountInterceptor;

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setReadTimeout(Duration.ofSeconds(3L))
                .setConnectTimeout(Duration.ofSeconds(3L))
                .build();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getAccountInterceptor)
                .addPathPatterns("/usr/member/appointment");
    }
}
