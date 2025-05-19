package com.example.orderstatbatch.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Order {
    private Long id;                   // 주문 ID
    private String status;             // 주문 상태 (예: PENDING, SHIPPED, CANCELLED)
    private LocalDateTime orderDate;   // 주문 날짜
    private Double amount;             // 주문 금액
    private Long customerId;           // 고객 ID
    private List<OrderDetails> orderDetails;
}
