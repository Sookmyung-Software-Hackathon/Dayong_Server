package com.smswh.smswh_backend.service;

import com.smswh.smswh_backend.config.auth.PrincipalDetails;
import com.smswh.smswh_backend.domain.Menu;
import com.smswh.smswh_backend.dto.MenuDto;
import com.smswh.smswh_backend.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public Menu addMenu(MenuDto menuDto, PrincipalDetails principalDetails){

        menuDto.setUser(principalDetails.getUser());
        Menu menu = menuDto.toEntity();

        return menuRepository.save(menu);

    }
}
