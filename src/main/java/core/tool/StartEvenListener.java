package core.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.StringUtils;

@Configuration
public class StartEvenListener {

    private static final Logger log = LoggerFactory.getLogger(StartEvenListener.class);

    public StartEvenListener() {
    }

    @Async
    @Order
    @EventListener(WebServerInitializedEvent.class)
    public void afterStart(WebServerInitializedEvent event) {
        Environment environment = event.getApplicationContext().getEnvironment();
        String appName = environment.getProperty("spring.application.name").toUpperCase();
        int port = event.getWebServer().getPort();
        String profile = StringUtils.arrayToCommaDelimitedString(environment.getActiveProfiles());
        log.info("--[{}]--启动完成, 当前使用端口[{}],环境变量为[{}]", appName, port, profile);
    }
}
