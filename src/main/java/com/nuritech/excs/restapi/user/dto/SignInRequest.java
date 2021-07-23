package com.nuritech.excs.restapi.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SignInRequest {
    private String email;
    private String password;
}
