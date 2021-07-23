package com.nuritech.excs.restapi.user.util;

public interface JwtUtil {
    String createToken();
    void verifyToken(String givenToken);
}
