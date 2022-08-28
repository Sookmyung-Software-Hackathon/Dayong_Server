package com.smswh.smswh_backend.controller;

import com.smswh.smswh_backend.config.auth.PrincipalDetails;
import com.smswh.smswh_backend.domain.user.User;
import com.smswh.smswh_backend.dto.PointDto;
import com.smswh.smswh_backend.dto.PostUser;
import com.smswh.smswh_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("join")  // 회원가입 api
    public User join(@RequestBody PostUser postUser){
        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        //user.setRole("ROLE_USER");
        //userRepository.save(user);
        return userService.PostUser(postUser);
        //return "회원가입 완료";
    }

    @GetMapping("/api/v1/user")
    public String user(Authentication authentication){
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        return "user " + principalDetails.getUsername() + "!!";
    }

//    @PatchMapping("/auth/user/point")
//    public ResponseEntity<?> addPoint(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody int point){
//        User user = userService.addPoint(principalDetails, point);
//        System.out.println("UserController : " + point);
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }

    @PatchMapping("/auth/point")
    public ResponseEntity<?> addPoint(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody PointDto pointDto){
        User user = userService.addPoint(principalDetails, pointDto);
        System.out.println("UserController : " + pointDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}


