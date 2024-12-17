package dev.ivanov.tour_sales.services;

import com.auth0.jwt.interfaces.Claim;
import dev.ivanov.tour_sales.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JwtUtils {

    @Value("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.expiration-seconds}")
    private Long expirationSeconds;

    @Value("${app.jwt.subject}")
    private String subject;

    @Value("${app.jwt.issuer}")
    private String issuer;

    public String generate(User user) {
        return null
    }

    public Map<String, Claim> verifyAndRetrieveClaims(String token) {
        return null;
    }
}
