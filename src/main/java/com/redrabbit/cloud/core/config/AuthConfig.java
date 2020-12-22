package com.redrabbit.cloud.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
public class AuthConfig {

    @Value("${app.key:1234567890-1234567890-1234567890}")
    private String appKey;

    @Value("${app.isuse:false}")
    private boolean isuse;

    public String getAppKey() {
        return appKey;
    }

    private void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public boolean isIsuse() {
        return isuse;
    }

    private void setIsuse(boolean isuse) {
        this.isuse = isuse;
    }
}
