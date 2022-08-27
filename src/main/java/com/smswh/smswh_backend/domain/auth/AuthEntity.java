package com.smswh.smswh_backend.domain.auth;

import com.smswh.smswh_backend.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Getter
@RequiredArgsConstructor
@Table(name = "auth")
@Entity
public class AuthEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String refreshToken;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Builder
    public AuthEntity(String refreshToken, User user){
        this.refreshToken = refreshToken;
        this.user = user;
    }

    public void refreshUpdate(String refreshToken){
        this.refreshToken = refreshToken;
    }
}
