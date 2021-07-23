package com.nuritech.excs.restapi.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SignInResponse {
    private String token;
    private String userId;
}
