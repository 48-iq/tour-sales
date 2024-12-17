package dev.ivanov.tour_sales.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.JWTVerifier;
import dev.ivanov.tour_sales.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;
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

    public String generate(User user, List<String> roles) {
        String token = JWT.create()
                .withSubject(subject)
                .withIssuer(issuer)
                .withIssuedAt(ZonedDateTime.now().toInstant())
                .withExpiresAt(ZonedDateTime.now().plusSeconds(expirationSeconds).toInstant())
                .withClaim("username", user.getUsername())
                .withClaim("roles", roles)
                .withClaim("id", user.getId())
                .sign(Algorithm.HMAC256(secret));
        return token;
    }

    public Map<String, Claim> verifyAndRetrieveClaims(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withClaimPresence("username")
                .withClaimPresence("id")
                .withClaimPresence("roles")
                .withSubject(subject)
                .withIssuer(issuer)
                .build();
        Map<String, Claim> claims = verifier.verify(token).getClaims();
        return claims;
    }
}
