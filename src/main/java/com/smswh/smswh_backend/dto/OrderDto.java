package com.smswh.smswh_backend.dto;

import com.smswh.smswh_backend.domain.Order;
import com.smswh.smswh_backend.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Boolean dayong;  // 포장용기 여부
    private int total;
    private User user;
    private Boolean delivery;


    public Order toEntity(Boolean dayong, int total, Boolean delivery){
        return Order.builder()
                .status("주문")
                .dayong(dayong)
                .total(total)
                .delivery(delivery)
                .build();
    }


}
