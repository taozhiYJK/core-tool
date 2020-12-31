package com.redrabbit.cloud.core.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redrabbit.cloud.core.filter.RequestWapper;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@Aspect
@Configuration
public class RequestLogAspect {

    private final static Logger log = LoggerFactory.getLogger(RequestLogAspect.class);

    @Around("@within(org.springframework.web.bind.annotation.RestController) || @within(org.springframework.stereotype.Controller)")
    public Object aroundApi(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();
        ObjectMapper objectMapper = new ObjectMapper();
        MethodSignature ms = (MethodSignature) point.getSignature();
        Method method = ms.getMethod();
        String methodName = method.getName();
        String className = ms.getDeclaringTypeName().substring(ms.getDeclaringTypeName().lastIndexOf("."));

        RequestWapper request = (RequestWapper)((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String methodType = request.getMethod();
        String servletPath = request.getServletPath();
        Map<String, String[]> parameterMap = request.getParameterMap();
        String requestParem = null;
        if (!parameterMap.isEmpty()) {
            StringBuffer param = new StringBuffer();
            parameterMap.forEach((k, v) -> {
                param.append(k).append("=").append(v[0]).append("&");
            });
            requestParem = param.toString().substring(0, param.toString().lastIndexOf("&"));
        }
        byte[] body = request.getBody();
        String requestBody = null;
        if(body.length>0) {
            requestBody = new String(body).replace("\r\n", "").replace(" ", "");
        }
        StringBuffer logBuffer = new StringBuffer("\n===========================  Request Start  ========================\n");
        List<String> logArgs = new ArrayList();
        logBuffer.append("====>  MethodType:{} Class&&Method {}.{}\n");
        logArgs.add(methodType);
        logArgs.add(className);
        logArgs.add(methodName);
        logBuffer.append("URL:{}\n");
        logArgs.add(servletPath);
        if (StringUtils.isNotBlank(requestParem)) {
            logBuffer.append("RequestParem :  {}\n");
            logArgs.add(requestParem);
        }
        if (StringUtils.isNotBlank(requestBody)) {
            logBuffer.append("RequestBody :  {}\n");
            logArgs.add(requestBody);
        }

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String harderName = headerNames.nextElement();
            String headerValue = request.getHeader(harderName);
            logBuffer.append("=== hander ===   {} : {}\n");
            logArgs.add(harderName);
            logArgs.add(headerValue);
        }

        Object res = point.proceed();
        String resStr = objectMapper.writeValueAsString(res);


        logBuffer.append("=== result ===      {}\n");
        logArgs.add(resStr);
        logBuffer.append("<==== 花费时间:{}ms\n");
        logArgs.add(String.valueOf(System.currentTimeMillis() - startTime));
        logBuffer.append("============================  Request End  =========================\n");
        log.info(logBuffer.toString(), logArgs.toArray());
        return res;
    }

}
