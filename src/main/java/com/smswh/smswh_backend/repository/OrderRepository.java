package com.smswh.smswh_backend.repository;

import com.smswh.smswh_backend.domain.Order;
import com.smswh.smswh_backend.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);

    long countByUser(User user);

    Order findByStatusAndUserId(String status, Long userId);
}
