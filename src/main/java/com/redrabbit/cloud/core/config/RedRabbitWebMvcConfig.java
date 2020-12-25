package com.redrabbit.cloud.core.config;

import com.redrabbit.cloud.core.interceptor.RedRabbitInterceptor;
import com.redrabbit.cloud.core.interceptor.SecrutyRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RedRabbitWebMvcConfig implements WebMvcConfigurer {

    private SecrutyRegistry secrutyRegistry;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //权限拦截器
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(urlInterceptor());
        interceptorRegistration.addPathPatterns("/**");
        interceptorRegistration.excludePathPatterns(this.secrutyRegistry.getTxcludePathPatterns());
    }

    @Bean
    public RedRabbitInterceptor urlInterceptor() {
        return new RedRabbitInterceptor();
    }

    public RedRabbitWebMvcConfig(SecrutyRegistry secrutyRegistry) {
        this.secrutyRegistry = secrutyRegistry;
    }

}
