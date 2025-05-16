package com.example.orderstatbatch.mapper;

import com.example.orderstatbatch.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
    List<Order> findAllOrders();
}
