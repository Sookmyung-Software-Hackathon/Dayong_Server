package com.smswh.smswh_backend.service;

import com.smswh.smswh_backend.config.auth.PrincipalDetails;
import com.smswh.smswh_backend.domain.Order;
import com.smswh.smswh_backend.domain.user.User;
import com.smswh.smswh_backend.dto.OrderDto;
import com.smswh.smswh_backend.repository.OrderRepository;
import com.smswh.smswh_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Transactional
    public Order order(PrincipalDetails principalDetails, OrderDto orderDto){
        User user = userRepository.findById(principalDetails.getUser().getId());
        Order orderEntity = orderDto.toEntity(orderDto.getDayong(), orderDto.getTotal(), orderDto.getDelivery(), orderDto.getAddress());
        orderEntity.setUser(user);

        return orderRepository.save(orderEntity);
    }

    @Transactional(readOnly = true)
    public List<Order> orderList(PrincipalDetails principalDetails){
        User user = userRepository.findById(principalDetails.getUser().getId());
//        List<Order> orders = orderRepository.findByUserId(user.getId());

        return orderRepository.findByUserId(user.getId());
    }

    @Transactional
    public Map mypage(PrincipalDetails principalDetails, Order order){
        Map<String, String> map = new HashMap<>();
        String username = principalDetails.getUser().getNickname();
        long counts = orderRepository.countByUser(principalDetails.getUser());
        int point = principalDetails.getUser().getPoint();

        map.put("user", username);
        map.put("point", String.valueOf(point));
        map.put("주문수", String.valueOf(counts));

        return map;
    }

}
