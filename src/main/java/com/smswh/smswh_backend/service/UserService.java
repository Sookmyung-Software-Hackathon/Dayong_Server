package com.smswh.smswh_backend.service;

import com.smswh.smswh_backend.config.auth.PrincipalDetails;
import com.smswh.smswh_backend.domain.Order;
import com.smswh.smswh_backend.domain.user.User;
import com.smswh.smswh_backend.dto.PointDto;
import com.smswh.smswh_backend.dto.PostUser;
import com.smswh.smswh_backend.repository.OrderRepository;
import com.smswh.smswh_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final OrderRepository orderRepository;

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

    @Transactional
    public User addPoint(PrincipalDetails principalDetails, PointDto pointDto){
        User user = userRepository.findById(principalDetails.getUser().getId());
        user.setPoint(pointDto.getPoint());
        Order order = orderRepository.findByStatusAndUserId("주문", user.getId());
        order.setStatus("주문완료");
        return user;
    }
}
