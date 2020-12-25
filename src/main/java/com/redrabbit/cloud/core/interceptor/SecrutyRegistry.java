package com.redrabbit.cloud.core.interceptor;

import java.util.ArrayList;
import java.util.List;

public class SecrutyRegistry {

    private final List<String> excludePathPatterns = new ArrayList<>();

    public SecrutyRegistry excludePathPatterns(String excludePathPattern) {
        excludePathPatterns.add(excludePathPattern);
        return this;
    }

    public List<String> getTxcludePathPatterns() {
        return this.excludePathPatterns;
    }
}
