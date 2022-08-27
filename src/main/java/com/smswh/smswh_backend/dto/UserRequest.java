package com.smswh.smswh_backend.dto;

import lombok.Getter;

@Getter
public class UserRequest {
    private String userId;
    private String userPassword;
    private String email;
}