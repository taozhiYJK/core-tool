package com.redrabbit.cloud.core.config;

import com.redrabbit.cloud.core.interceptor.RedRabbitInterceptor;
import com.redrabbit.cloud.core.interceptor.SecrutyRegistry;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@SpringBootApplication
public class RedRabbitWebMvcConfig implements WebMvcConfigurer {

    private SecrutyRegistry secrutyRegistry;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

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

    @Bean
    public Docket createRestApi() {
        String appName = System.getProperty("spring.application.name");
        String packName = appName.substring(appName.lastIndexOf("-") + 1);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.redrabbit.cloud."+ packName +".controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("RedRabbit的测试项目")
                .description("swagger-bootstrap-ui")
                //.termsOfServiceUrl("http://localhost:8999/")
                .contact("RedRabbit")
                .version("1.0")
                .build();
    }

}
