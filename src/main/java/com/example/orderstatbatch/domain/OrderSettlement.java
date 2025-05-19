package com.example.orderstatbatch.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class OrderSettlement {
    private int id;
    private LocalDate settlementDate;
    private int totalOrderCount;
    private int totalCompletedCount;
    private int totalCancelledCount;
    private double totalAmount;
    private double completedAmount;
    private double cancelledAmount;
    private double averageOrderAmount;
}