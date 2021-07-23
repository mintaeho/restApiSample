package com.nuritech.excs.restapi.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String userEmail;
    private String userPassword;
    private String token;

    @Builder
    public User(String userEmail, String userPassword, String token) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.token = token;
    }
}
