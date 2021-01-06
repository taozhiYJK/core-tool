package com.redrabbit.cloud.core.exec;

import com.redrabbit.cloud.core.api.ResMod;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class GlobaController {

    @ExceptionHandler(Exception.class)
    public ResMod exceptionHandle(HttpServletRequest request, Exception e) {
        log.error("系统错误: 请求url:{} {}--> {} --> {}", request.getMethod(), request.getServletPath(), e.getClass().getSimpleName(), e);
        return ResMod.fail().msg("系统错误");
    }

    @ExceptionHandler(ClassCastException.class)
    public ResMod classCastExceptionHandle(HttpServletRequest request, Exception e) {
        return ResMod.fail().msg("无效的请求路径");
    }

    @ExceptionHandler(JwtException.class)
    public ResMod jwtExceptioHnandle(Exception e) {
        log.error("token解析异常: {} --> {}", e.getClass().getSimpleName(), e.getMessage());
        return ResMod.fail().msg("token错误");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResMod handleMessageNotReadableException(HttpMessageNotReadableException e) {
        String[] messages = e.getMessage().split(":");
        String message = messages[1];
        message = message.substring(message.indexOf("\"")+1, message.length()-1);
        return ResMod.fail().msg(String.format("输入参数[%s]类型不正确", message));
        //return ResMod.fail().msg(String.valueOf(e));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResMod methodArgumentNotValidExceptionhandle(MethodArgumentNotValidException e) {
        return ResMod.fail().msg(e.getBindingResult().getFieldError().getDefaultMessage());
    }

}
