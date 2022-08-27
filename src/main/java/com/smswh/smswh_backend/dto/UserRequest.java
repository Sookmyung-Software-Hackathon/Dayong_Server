package com.smswh.smswh_backend.dto;

import lombok.Getter;

@Getter
public class UserRequest {
    private String username;
//    private String userPassword;
    private String password;
    private String email;
}