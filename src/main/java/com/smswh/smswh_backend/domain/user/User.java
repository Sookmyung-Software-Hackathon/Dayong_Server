package com.smswh.smswh_backend.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id  // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

//    @Column(nullable = false, unique = true)
//    private String username;

    @Column(nullable = false)
    private String password;

//    @Column(nullable = false)
    private String email;

    public User(String userId, String username, String password, String email){
//        this.username = username;
        this.userId = userId;
        this.password = password;
        this.email = email;
    }
}
