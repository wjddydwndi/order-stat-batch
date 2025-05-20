package com.example.orderstatbatch.service;

import com.example.orderstatbatch.domain.OrderSettlement;

public interface OrderSettlementService {

    void insertSettlement(OrderSettlement summary);

    OrderSettlement selectLatestSettlement();
}
