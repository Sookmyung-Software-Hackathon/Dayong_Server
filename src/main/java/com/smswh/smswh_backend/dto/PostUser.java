package com.smswh.smswh_backend.dto;

import com.smswh.smswh_backend.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostUser {
    private String username;
    private String nickname;
    private String password;
    private String role = "ROLE_USER";

    public User toEntity(String username, String role, String nickname, String password){
        return User.builder()
                .username(username)
                .nickname(nickname)
                .roles(role)
                .password(password).build();
    }
}
