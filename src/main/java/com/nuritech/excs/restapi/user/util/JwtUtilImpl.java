package com.nuritech.excs.restapi.user.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtilImpl implements JwtUtil {
    private String TEST_SIGN_KEY = "${restapi.testSignKey}";
    private String ISSUER = "${restapi.issuer}";
    private Date EXPIRED_TIME = new Date(System.currentTimeMillis() + 1000 * 60);

    @Override
    public String createToken() {
        return JWT.create()
                .withIssuer(ISSUER)
                .withExpiresAt(EXPIRED_TIME)
                .sign(Algorithm.HMAC256(TEST_SIGN_KEY));
    }

    @Override
    public void verifyToken(String givenToken) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TEST_SIGN_KEY))
                .withIssuer(ISSUER)
                .build();
        verifier.verify(givenToken);
    }
}
