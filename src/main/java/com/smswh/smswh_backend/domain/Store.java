package com.smswh.smswh_backend.domain;

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
public class Store {

    @Id  // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    private String address;
    private String contact;

}
