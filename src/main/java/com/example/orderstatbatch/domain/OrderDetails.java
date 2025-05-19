package com.example.orderstatbatch.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetails {
    private long order_id;
    private long productId;
    private int quantity;
    private double totalPrice;
}
