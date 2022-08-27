package com.smswh.smswh_backend.repository;

import com.smswh.smswh_backend.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(String userId);
    Optional<User> findByUserIdAndPassword(String userId, String userpassword);
}
