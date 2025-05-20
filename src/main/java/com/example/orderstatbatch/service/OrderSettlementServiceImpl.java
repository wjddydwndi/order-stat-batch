package com.example.orderstatbatch.service;

import com.example.orderstatbatch.domain.OrderSettlement;
import com.example.orderstatbatch.mapper.OrderSettlementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderSettlementServiceImpl implements OrderSettlementService{

    private final OrderSettlementMapper orderSettlementMapper;

    @Override
    public void insertSettlement(OrderSettlement summary) {
        orderSettlementMapper.insertSettlement(summary);
    }

    @Override
    public OrderSettlement selectLatestSettlement() {
        return orderSettlementMapper.selectLatestSettlement();;
    }


}
