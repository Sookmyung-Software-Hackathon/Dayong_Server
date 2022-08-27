package com.smswh.smswh_backend.service;

import com.smswh.smswh_backend.config.jwt.TokenUtils;
import com.smswh.smswh_backend.domain.auth.AuthEntity;
import com.smswh.smswh_backend.repository.AuthRepository;
import com.smswh.smswh_backend.domain.user.User;
import com.smswh.smswh_backend.repository.UserRepository;
import com.smswh.smswh_backend.dto.TokenResponse;
import com.smswh.smswh_backend.dto.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TokenUtils tokenUtils;
    private final AuthRepository authRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Transactional
    public TokenResponse signUp(UserRequest userRequest) {  // 회원가입

//        String rawPassword = userRequest.getUserPassword();
//        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        User usersEntity =
                userRepository.save(
                        User.builder()
                                .password(userRequest.getUserPassword())
                                .username(userRequest.getUsername())
                                .email(userRequest.getEmail())
                                .build());

        // 회원가입시 토큰 생성해주기
        String accessToken = tokenUtils.generateJwtToken(usersEntity);
        String refreshToken = tokenUtils.saveRefreshToken(usersEntity);

        // 생성한 토큰 authRepo에 저장
        authRepository.save(
                AuthEntity.builder().user(usersEntity).refreshToken(refreshToken).build());

        return TokenResponse.builder()
                .ACCESS_TOKEN(accessToken)
                .REFRESH_TOKEN(refreshToken).build();
    }

    @Transactional
    public TokenResponse signIn(UserRequest userRequest) {  // 로그인
        User usersEntity =
                userRepository
                        .findByUsernameAndPassword(userRequest.getUsername(), userRequest.getUserPassword())  // 유저 id랑 password로 가입된 사용자인지 찾아주기
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        AuthEntity authEntity =
                authRepository
                        .findByUserId(usersEntity.getId())  // 토큰이 올바른지 확인
                        .orElseThrow(() -> new IllegalArgumentException("Token 이 존재하지 않습니다."));
        String accessToken = "";
        String refreshToken= authEntity.getRefreshToken();

        if (tokenUtils.isValidRefreshToken(refreshToken)) {  // 토큰이 유효한지 확인
            accessToken = tokenUtils.generateJwtToken(authEntity.getUser());
            return TokenResponse.builder()
                    .ACCESS_TOKEN(accessToken)
                    .REFRESH_TOKEN(authEntity.getRefreshToken())
                    .build();
        } else {
            refreshToken = tokenUtils.saveRefreshToken(usersEntity);
            authEntity.refreshUpdate(refreshToken);
        }

        return TokenResponse.builder().ACCESS_TOKEN(accessToken).REFRESH_TOKEN(refreshToken).build();
    }
}
