package com.nuritech.excs.restapi.user.web;

import com.nuritech.excs.restapi.user.dto.SignInRequest;
import com.nuritech.excs.restapi.user.dto.SignInResponse;
import com.nuritech.excs.restapi.user.dto.SignUpRequest;
import com.nuritech.excs.restapi.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserApiController {
    @Autowired
    private UserService userService;

    @PostMapping("/signUp")
    public String signUp(@RequestBody SignUpRequest signUpRequest) {
        log.debug(">>>> signUpRequest email {}, password {}", signUpRequest.getEmail(), signUpRequest.getPassword());

        userService.signUp(signUpRequest);
        return "Sign Up OK";
    }

    @PostMapping("/signIn")
    public SignInResponse signIn(@RequestBody SignInRequest signInRequest) {
        return userService.signIn(signInRequest);
    }

    @PostMapping("/test")
    public void test() {
        log.debug(">>>> test success...");
    }
}
