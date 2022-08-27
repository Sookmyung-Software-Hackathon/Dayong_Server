package com.smswh.smswh_backend.service;

import com.smswh.smswh_backend.config.auth.PrincipalDetails;
import com.smswh.smswh_backend.domain.Order;
import com.smswh.smswh_backend.domain.user.User;
import com.smswh.smswh_backend.dto.OrderDto;
import com.smswh.smswh_backend.repository.OrderRepository;
import com.smswh.smswh_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Transactional
    public Order order(PrincipalDetails principalDetails, OrderDto orderDto){
        User user = userRepository.findById(principalDetails.getUser().getId());
        Order orderEntity = orderDto.toEntity(orderDto.getDayong(), orderDto.getTotal(), orderDto.getDelivery());
        orderEntity.setUser(user);

        return orderRepository.save(orderEntity);
    }

}
