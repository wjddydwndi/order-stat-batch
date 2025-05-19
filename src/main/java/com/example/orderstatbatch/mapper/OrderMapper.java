package com.example.orderstatbatch.mapper;

import com.example.orderstatbatch.domain.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
    List<Order> findOrdersWithDetails(@Param("offset") int offset, @Param("limit") int limit);

}
