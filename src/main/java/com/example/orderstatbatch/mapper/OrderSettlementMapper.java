package com.example.orderstatbatch.mapper;

import com.example.orderstatbatch.domain.OrderSettlement;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderSettlementMapper {
    void insertSettlement(OrderSettlement orderSettlement);

    OrderSettlement selectLatestSettlement();
}
