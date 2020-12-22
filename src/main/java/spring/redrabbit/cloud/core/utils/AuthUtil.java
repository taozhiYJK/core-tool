package spring.redrabbit.cloud.core.utils;

import com.alibaba.fastjson.JSONObject;
import com.redrabbit.cloud.config.AuthConfig;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import spring.redrabbit.cloud.core.entity.UserInfo;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.crypto.SecretKey;

@Component
public class AuthUtil {

    @Resource
    private AuthConfig authConfig;

    public String createJwt(UserInfo authUser) {
        String encode = new BASE64Encoder().encode(authConfig.getAppKey().getBytes());
        SecretKey secretKey = Keys.hmacShaKeyFor(encode.getBytes());
        JwtBuilder builder = Jwts.builder().setSubject(JSONObject.toJSONString(authUser)).signWith(secretKey);
        //builder.set
        //builder.setExpiration()
        String token = builder.compact();
        return token;
    }

}
