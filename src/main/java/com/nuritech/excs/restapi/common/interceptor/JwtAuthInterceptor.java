package com.nuritech.excs.restapi.common.interceptor;

import com.nuritech.excs.restapi.user.domain.User;
import com.nuritech.excs.restapi.user.domain.UserRepository;
import com.nuritech.excs.restapi.user.service.UserService;
import com.nuritech.excs.restapi.user.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class JwtAuthInterceptor implements HandlerInterceptor {

    private JwtUtil jwtUtil;
    private UserRepository userRepository;
    private String HEADER_TOKEN_KEY;

    public JwtAuthInterceptor(UserRepository userRepository, JwtUtil jwtUtil, String headerTokenKey) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.HEADER_TOKEN_KEY = headerTokenKey;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                              HttpServletResponse response,
                              Object handler) throws Exception {

        log.debug("=============== preHandler ==============");
        if(StringUtils.isEmpty(request.getHeader("userId")))
            throw new IllegalArgumentException("사용자 ID를 확인 할 수 없습니다.");

        log.debug(">>>> getHeader userId = {}", request.getHeader("userId"));
        log.debug(">>>> 2 getHeader userId = {}", Long.parseLong(request.getHeader("userId")) );

        User user = userRepository.findById(Long.parseLong(request.getHeader("userId")))
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자 입니다."));

        log.debug(">>>> HEADER_TOKEN_KEY={}", HEADER_TOKEN_KEY);
        String givenToken = request.getHeader(HEADER_TOKEN_KEY);
        log.debug(">>>> givenToken={}, user.getToken()={}", givenToken, user.getToken());
        verifyToken(givenToken, user.getToken());

        return true;
    }

    private void verifyToken(String givenToken,
                             String membersToken) {
        if (! givenToken.equals(membersToken)) {
            throw new IllegalArgumentException("사용자의 Token과 일치하지 않습니다.");
        }
        jwtUtil.verifyToken(givenToken);
    }


}
