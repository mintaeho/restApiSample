package com.nuritech.excs.restapi.user.service;

import com.nuritech.excs.restapi.user.domain.User;
import com.nuritech.excs.restapi.user.domain.UserRepository;
import com.nuritech.excs.restapi.user.dto.SignInRequest;
import com.nuritech.excs.restapi.user.dto.SignInResponse;
import com.nuritech.excs.restapi.user.dto.SignUpRequest;
import com.nuritech.excs.restapi.user.dto.SignUpResponse;
import com.nuritech.excs.restapi.user.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    public void signUp(SignUpRequest signUpRequest) {
        verifyDuplicatedUser(signUpRequest.getEmail());

        User newUser = User.builder()
                .userEmail(signUpRequest.getEmail())
                .userPassword(signUpRequest.getPassword())
                .token(jwtUtil.createToken())
                .build();

        userRepository.save(newUser);
    }

    private void verifyDuplicatedUser(String userEmail) {
        if(userRepository.findByUserEmail(userEmail).isPresent())
            throw new IllegalArgumentException("중복된 사용자 입니다.");
    }

    public SignInResponse signIn(SignInRequest signInRequest) {
        User findUser = userRepository.findByUserEmail(signInRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자 입니다."));

        if (! findUser.getUserPassword().equals(signInRequest.getPassword()))
            throw new IllegalArgumentException("암호가 일치하지 않습니다.");

        return new SignInResponse(findUser.getToken(), findUser.getUserId().toString());
    }

    @Transactional(readOnly = true)
    public List<SignUpResponse> findAllDesc() {
        return userRepository.findAllDesc().stream()
                .map(SignUpResponse::new)
                .collect(Collectors.toList());
    }


}
