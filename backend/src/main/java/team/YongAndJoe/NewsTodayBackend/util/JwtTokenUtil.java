package team.YongAndJoe.NewsTodayBackend.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import team.YongAndJoe.NewsTodayBackend.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtTokenUtil {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    private String secret;
    private int validity;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public int getValidity() {
        return validity;
    }

    public void setValidity(int validity) {
        this.validity = validity;
    }

    public String getTokenFromRequest(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        // JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token.
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return token;
    }

    public String generateJwt(User user) {
        Date expirationTime = new Date(System.currentTimeMillis() + validity * 1000);
        String token =Jwts
                .builder()
                .setIssuer(user.getUsername())
                .signWith(SignatureAlgorithm.HS512, secret)
                .setExpiration(expirationTime)
                .compact();
        redisTemplate.opsForValue().set(token, expirationTime);
        return token;
    }

    public boolean validJwt(String token) {
        return token != null && redisTemplate.hasKey(token) && ((Date)redisTemplate.opsForValue().get(token)).after(new Date());
    }

    public boolean invalidateJwt(String token) {
        if (token == null || !redisTemplate.hasKey(token)) {
            return false;
        }
        redisTemplate.delete(token);
        return true;
    }
}