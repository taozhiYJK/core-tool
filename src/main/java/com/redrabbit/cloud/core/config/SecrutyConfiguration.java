package com.redrabbit.cloud.core.config;

import com.redrabbit.cloud.core.interceptor.SecrutyRegistry;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Order
@Configuration
@AutoConfigureBefore(RedRabbitWebMvcConfig.class)
public class SecrutyConfiguration {

    @Bean
    @ConditionalOnMissingBean(SecrutyRegistry.class)
    public SecrutyRegistry secrutyRegistry() {
        return new SecrutyRegistry();
    }

}
