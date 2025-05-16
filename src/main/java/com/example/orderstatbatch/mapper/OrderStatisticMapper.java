package com.example.orderstatbatch.mapper;

import com.example.orderstatbatch.domain.OrderStatistic;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatisticMapper {
    OrderStatistic findByOrderStatus(String orderStatus);

}
