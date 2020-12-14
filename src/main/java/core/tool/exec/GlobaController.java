package core.tool.exec;

import core.tool.api.ResMod;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobaController {

    @ExceptionHandler(Exception.class)
    public ResMod exceptionhandle(Exception e) {
        return ResMod.fail().msg("系统错误");
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
