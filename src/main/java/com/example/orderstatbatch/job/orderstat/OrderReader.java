package com.example.orderstatbatch.job.orderstat;

import com.example.orderstatbatch.domain.Order;
import com.example.orderstatbatch.mapper.OrderMapper;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderReader extends ListItemReader<Order> implements InitializingBean {

    private final OrderMapper orderMapper;

    public OrderReader(OrderMapper orderMapper) {
        super(List.of());  // 초기에는 빈 리스트
        this.orderMapper = orderMapper;
    }

    @Override
    public void afterPropertiesSet() {
        // MyBatis를 통해 DB에서 주문 목록을 읽어와 설정
        List<Order> orders = orderMapper.findAllOrders();
        this.setDelegate(new ListItemReader<>(orders));
    }
}
