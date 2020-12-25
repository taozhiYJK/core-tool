package com.redrabbit.cloud.core.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.redrabbit.cloud.core.annotation.PreAuth;
import com.redrabbit.cloud.core.api.ResMod;
import com.redrabbit.cloud.core.entity.UserInfo;
import com.redrabbit.cloud.core.utils.AuthUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class RedRabbitInterceptor implements HandlerInterceptor {

    Logger log =  LoggerFactory.getLogger(RedRabbitInterceptor.class);

    @Resource
    private AuthUtil authUtil;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("进入拦截器preHandle: {}", handler);
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        String token = request.getHeader("token");
        if (token == null) {
            sendResponse(response, "无效的token");
            return false;
        }

        UserInfo userInfo = (UserInfo) authUtil.resolveJwt(token, UserInfo.class);
        if (method.isAnnotationPresent(PreAuth.class)) {
            PreAuth declaredAnnotation = method.getDeclaredAnnotation(PreAuth.class);
            List<String> auths = Arrays.asList(declaredAnnotation.auths());
            if (auths.contains(userInfo.getLevel())) {
                return true;
            } else {
                sendResponse(response, "无效的token");
                return false;
            }

        }
        return true;
    }

    public void sendResponse(HttpServletResponse response, String messsage) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(200);
            ResMod resMod = ResMod.fail().msg(messsage);
            response.getWriter().print(JSONObject.toJSONString(resMod));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
