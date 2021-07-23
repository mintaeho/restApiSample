package com.nuritech.excs.restapi.common.config;

import com.nuritech.excs.restapi.common.interceptor.JwtAuthInterceptor;
import com.nuritech.excs.restapi.user.domain.UserRepository;
import com.nuritech.excs.restapi.user.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private String[] INTERCEPTOR_WHITE_LIST = {
            "/signUp/**",
            "/signIn/**",
    };

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;

    @Value(value = "${restapi.headerTokenKey}")
    private String headerTokenKey;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtAuthInterceptor(userRepository, jwtUtil, headerTokenKey))
                .addPathPatterns("/*")
                .excludePathPatterns("/index", "/signUp", "/signIn");
    }
}
