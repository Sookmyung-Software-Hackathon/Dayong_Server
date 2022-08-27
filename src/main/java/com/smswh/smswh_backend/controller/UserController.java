package com.smswh.smswh_backend.controller;

import com.smswh.smswh_backend.domain.user.User;
import com.smswh.smswh_backend.dto.TokenResponse;
import com.smswh.smswh_backend.dto.UserRequest;
import com.smswh.smswh_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/user/signUp")
    public ResponseEntity signUp(@RequestBody UserRequest userRequest) {
//        System.out.println("컨트롤러");
        return userService.findByUserId(userRequest.getUserId()).isPresent()
                ? ResponseEntity.badRequest().build()
                : ResponseEntity.ok(userService.signUp(userRequest));
    }

    @PostMapping("/user/signIn")
    public ResponseEntity<TokenResponse> signIn(@RequestBody UserRequest userRequest) {

        return ResponseEntity.ok().body(userService.signIn(userRequest));
    }
}
