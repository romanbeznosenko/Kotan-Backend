package com.kotanapp.kotanappapi.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {
    private final UserDetailsInterceptor userDetailsInterceptor;
    private final UserInterceptor userInterceptor;
    private final InternalIntegrationInterceptor internalIntegrationInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //Load UserDAO
        registry.addInterceptor(userInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/team/list")
                .excludePathPatterns("/api/match/list")
                .excludePathPatterns("/api/team/{teamId}/player/list");

        registry.addInterceptor(internalIntegrationInterceptor)
                .addPathPatterns("/internal/**");


        //Build userDetails for SecurityContext
        registry.addInterceptor(userDetailsInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/team/list")
                .excludePathPatterns("/api/match/list")
                .excludePathPatterns("api/team/{teamId}/player/list")
                .addPathPatterns("/internal/**");
    }
}