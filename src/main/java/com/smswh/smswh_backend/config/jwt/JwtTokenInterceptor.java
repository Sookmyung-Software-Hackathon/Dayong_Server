package com.smswh.smswh_backend.config.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtTokenInterceptor implements HandlerInterceptor {

    private final TokenUtils tokenUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        System.out.println("preHandle안에");
        System.out.println("JwtToken 호출");
        String accessToken = request.getHeader("ACCESS_TOKEN");
        System.out.println("AccessToken:" + accessToken);
        System.out.println("여기여기");
        String refreshToken = request.getHeader("REFRESH_TOKEN");
        System.out.println("RefreshToken:" + refreshToken);

        if (accessToken != null) {
            if (tokenUtils.isValidToken(accessToken)) {  // 유효한 토큰인지 확인
                return true;
            }
        }
        response.setStatus(401);
        response.setHeader("ACCESS_TOKEN", accessToken);
        response.setHeader("REFRESH_TOKEN", refreshToken);
        response.setHeader("msg", "Check the tokens.");
        return false;
    }
}
