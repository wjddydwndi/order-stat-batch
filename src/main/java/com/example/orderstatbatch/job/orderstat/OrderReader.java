package com.example.orderstatbatch.job.orderstat;

import com.example.orderstatbatch.domain.Order;
import com.example.orderstatbatch.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class OrderReader implements ItemReader<Order>{

    private final OrderMapper orderMapper;

    private int currentIndex = 0;
    private List<Order> orderList;

    private int pageSize = 1000;// page 크기
    private int pageNumber = 0; // 현재 페이지 번호

    @Override
    public Order read() {
        if (orderList == null || currentIndex >= orderList.size()) {
            orderList = orderMapper.findOrdersWithDetails(pageNumber * pageSize, pageSize);
            currentIndex = 0; // 인덱스를 처음으로 초기화
            if (orderList == null || orderList.isEmpty()) {
                return null; // 읽을 데이터가 없으면 null
            }
            pageNumber++;
        }

        return orderList.get(currentIndex++);
    }
}
