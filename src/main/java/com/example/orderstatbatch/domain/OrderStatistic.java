package com.example.orderstatbatch.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderStatistic {
    private String orderStatus;   // 주문 상태 (예: PENDING, SHIPPED, CANCELLED 등)
    private int orderCount;       // 해당 상태의 주문 수
    private int totalAmount;      // 해당 상태의 총 주문 금액 (선택 사항)
    private String statDate;      // 통계 생성 날짜 (yyyy-MM-dd 형식)
}
