package com.smswh.smswh_backend.controller;

import com.smswh.smswh_backend.config.auth.PrincipalDetails;
import com.smswh.smswh_backend.domain.Order;
import com.smswh.smswh_backend.dto.OrderDto;
import com.smswh.smswh_backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/auth/order")  // 주문하기 api
    public ResponseEntity<?> order(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody OrderDto orderDto){
        Order order = orderService.order(principalDetails, orderDto);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/auth/mypage")
    public ResponseEntity<?> mypage(@AuthenticationPrincipal PrincipalDetails principalDetails, Order order){
        Map map = orderService.mypage(principalDetails, order);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/auth/order/orderList")  // 주문 내역 조회하기 api
    public List<Order> orderList(@AuthenticationPrincipal PrincipalDetails principalDetails){
        return orderService.orderList(principalDetails);
    }
}
