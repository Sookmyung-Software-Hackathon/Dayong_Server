package com.smswh.smswh_backend.domain;

import com.smswh.smswh_backend.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;

import javax.persistence.*;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;

    private String menuName;
    private Double price;
    private int num;

    @JoinColumn(name="storeId")
    @ManyToOne
    private Store store;

    @JoinColumn(name="userId")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;



}
