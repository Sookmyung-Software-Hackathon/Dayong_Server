package com.smswh.smswh_backend.controller;

import com.smswh.smswh_backend.config.auth.PrincipalDetails;
import com.smswh.smswh_backend.domain.Menu;
import com.smswh.smswh_backend.dto.MenuDto;
import com.smswh.smswh_backend.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping("/auth/menu")
    public ResponseEntity<?> addMenu(@RequestBody MenuDto menuDto, @AuthenticationPrincipal PrincipalDetails principalDetails){
        Menu menu = menuService.addMenu(menuDto, principalDetails);
        return new ResponseEntity<>(menu, HttpStatus.CREATED);
    }

}
