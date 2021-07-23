package com.nuritech.excs.restapi.user.dto;

import com.nuritech.excs.restapi.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SignUpResponse {
    private String email;
    private String password;
    private String token;

    public SignUpResponse(User entity) {
        this.email = entity.getUserEmail();
        this.password = entity.getUserPassword();
        this.token = entity.getToken();
    }
}

