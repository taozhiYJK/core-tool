package com.redrabbit.cloud.core.annotation;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AliasFor;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@SpringBootApplication
@EnableDiscoveryClient
public @interface RedRabbitBootApplication {

    @AliasFor(
            annotation = ComponentScan.class,
            attribute = "basePackages"
    )
    String[] scanBasePackages() default {"com.redrabbit.cloud"};

    @AliasFor(
            annotation = ComponentScan.class,
            attribute = "basePackageClasses"
    )
    Class<?>[] scanBasePackageClasses() default {};

    @AliasFor(
            annotation = EnableDiscoveryClient.class
    )
    boolean autoRegister() default true;

}



