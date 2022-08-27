package com.smswh.smswh_backend.repository;

import com.smswh.smswh_backend.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String username);
    User findById(long userId);
    User findByNickname(String nickname);
}
