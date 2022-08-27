package com.smswh.smswh_backend.domain;

import com.smswh.smswh_backend.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @JoinColumn(name="userId")
    @OneToOne(fetch = FetchType.EAGER)
    private User user;

    @JoinColumn(name="menuId")
    @OneToMany(fetch = FetchType.EAGER)
    private List<Menu> menu;

}
