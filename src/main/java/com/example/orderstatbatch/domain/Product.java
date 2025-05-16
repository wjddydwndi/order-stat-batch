package com.example.orderstatbatch.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private Long id;               // 상품 ID
    private String name;           // 상품 이름
    private Double price;          // 상품 가격
    private int stockQuantity;     // 상품 재고 수량
}
