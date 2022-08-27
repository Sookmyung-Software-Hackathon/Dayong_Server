package com.smswh.smswh_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TokenResponse {

    private String ACCESS_TOKEN;
    private String REFRESH_TOKEN;
}
