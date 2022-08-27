package com.smswh.smswh_backend.service;

import com.smswh.smswh_backend.domain.user.User;
import com.smswh.smswh_backend.dto.PostUser;
import com.smswh.smswh_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public User PostUser(PostUser postUser){
        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        //user.setRole("ROLE_USER");
        User user = postUser.toEntity(postUser.getUsername(),
                postUser.getRole(),
                postUser.getNickname(),
                bCryptPasswordEncoder.encode(postUser.getPassword()));
        return userRepository.save(user);
    }
}
