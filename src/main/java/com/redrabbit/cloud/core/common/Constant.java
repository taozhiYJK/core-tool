package com.redrabbit.cloud.core.common;

import java.util.HashMap;
import java.util.Map;

public class Constant {

    public static Map<String, String> routerMap = new HashMap<>();

    static {
        routerMap.put("redrabbit-auth", "安全服务");
        routerMap.put("feign-producer", "feign生产者");
        routerMap.put("feign-consumer", "feign消费者");
        routerMap.put("dubbo-producer", "dubbo生产者");
        routerMap.put("dubbo-consumer", "dubbo生产者");
    }
}
