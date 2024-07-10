package org.suhacan.justdoit.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.suhacan.justdoit.security.user.UserDetailImpl;

import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@Component
@Slf4j
public class TokenManager {

    @Value("suha")
    public String jwtSecret;
    @Value("86400000")
    public Long jwtExpirationMs;
    @Value("suha")
    public String issuer;

    public String generateJwtToken(Authentication authentication) {

        UserDetailImpl userPrincipal = (UserDetailImpl) authentication.getPrincipal();

        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + jwtExpirationMs * 1000);

        return JWT.create()
                .withSubject(userPrincipal.getUsername())
                .withClaim("ROLE", "ADMIN")
                .withExpiresAt(expirationDate)
                .withIssuer(issuer)
                .withIssuedAt(createdDate)
                .sign(HMAC512(jwtSecret));
    }

    public String subjectFromToken(String token){
        return verifyJwtToken(token).getSubject();
    }

    public DecodedJWT verifyJwtToken(String token) {
        Assert.hasText("Token can not be null", token);

        var jwtVerifier = JWT.require(HMAC512(jwtSecret)).build();
        var decodedJWT = jwtVerifier.verify(token.replace("Bearer ", ""));

        if (!validateToken(decodedJWT).equals(false)) return null;
        return decodedJWT;
    }

    private Boolean validateToken(DecodedJWT decodedJWT) {
        return decodedJWT.getExpiresAt().before(new Date(System.currentTimeMillis()));
    }
}
