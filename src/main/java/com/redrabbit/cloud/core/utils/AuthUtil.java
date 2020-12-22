package com.redrabbit.cloud.core.utils;

import com.alibaba.fastjson.JSONObject;
import com.redrabbit.cloud.core.config.AuthConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.crypto.SecretKey;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Component
public class AuthUtil {

    @Resource
    private AuthConfig authConfig;

    public String createJwt(Map<String, String> authUser, boolean isExpire) {
        String encode = new BASE64Encoder().encode(authConfig.getAppKey().getBytes());
        SecretKey secretKey = Keys.hmacShaKeyFor(encode.getBytes());
        JwtBuilder builder = Jwts.builder().setSubject(JSONObject.toJSONString(authUser)).signWith(secretKey);
        if (isExpire) {
            // 设置超时时间
            //DateUtil.format()
            //Calendar instance = Calendar.getInstance();
        }
        return builder.compact();
    }

    public Object resolveJwt(String token, Class clazz) {
        String encode = new BASE64Encoder().encode(authConfig.getAppKey().getBytes());
        SecretKey secretKey = Keys.hmacShaKeyFor(encode.getBytes());
        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
        return JSONObject.parseObject(claimsJws.getBody().getSubject(), clazz);
    }

}
