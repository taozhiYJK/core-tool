package com.redrabbit.cloud.core;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Properties;

public class RedRabbitCloudApplication {

    public RedRabbitCloudApplication() {
    }

    public static ConfigurableApplicationContext run (String appName, Class source, String... args){
        SpringApplicationBuilder builder = createSpringApplicationBuilder(appName, source);
        return builder.run(args);
    }

    public static SpringApplicationBuilder createSpringApplicationBuilder(String appName, Class source) {
        System.out.println("启动的服务名为:" + appName);
        Properties prop = System.getProperties();
        prop.setProperty("server.port", "${port:8080}");
        prop.setProperty("spring.application.name", appName);
        prop.setProperty("logging.config", "classpath:logback.xml");
        prop.setProperty("spring.cloud.nacos.discovery.server-addr", "192.168.100.110:8848");
        prop.setProperty("spring.cloud.nacos.config.server-addr", "192.168.100.110:8848");
        prop.setProperty("spring.cloud.nacos.config.file-extension", "yaml");
        prop.setProperty("spring.cloud.nacos.config.shared-dataids", "common.yaml");
        SpringApplicationBuilder builder = new SpringApplicationBuilder(new Class[]{source});
        return builder;
    }

}
