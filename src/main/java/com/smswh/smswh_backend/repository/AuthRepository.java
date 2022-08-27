package com.smswh.smswh_backend.repository;

import com.smswh.smswh_backend.domain.auth.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<AuthEntity, Long> {
    Optional<AuthEntity> findByUserId(Long userId);
}
