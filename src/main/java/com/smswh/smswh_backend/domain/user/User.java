package com.smswh.smswh_backend.domain.user;


import com.smswh.smswh_backend.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nickname;  // 아이디

    @Column(nullable = false)
    private String username;  // 유저 이름

    @Column(nullable = false)
    private String password;

    private String roles;
}
