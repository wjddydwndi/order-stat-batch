package com.example.orderstatbatch.job.orderstat;

import com.example.orderstatbatch.domain.OrderStatistic;
import com.example.orderstatbatch.mapper.OrderStatisticMapper;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderWriter implements ItemWriter<OrderStatistic> {

    private final OrderStatisticMapper orderStatisticMapper;

    public OrderWriter(OrderStatisticMapper orderStatisticMapper) {
        this.orderStatisticMapper = orderStatisticMapper;
    }

    @Override
    public void write(Chunk<? extends OrderStatistic> chunk) throws Exception {
        for (OrderStatistic stat : chunk) {
            // DB에 존재하는 통계가 있을 경우, 카운트만 갱신
            OrderStatistic existingStat = orderStatisticMapper.findByOrderStatus(stat.getOrderStatus());
            if (existingStat != null) {
                // 기존 통계 업데이트 (카운트 증가)
                existingStat.setOrderCount(existingStat.getOrderCount() + stat.getOrderCount());
                orderStatisticMapper.save(existingStat);
            } else {
                // 새로운 통계 추가
                orderStatisticMapper.save(stat);
            }
        }
    }
}