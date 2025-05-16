package com.example.orderstatbatch.job.orderstat;

import org.springframework.stereotype.Component;

package com.example.orderstatbatch.job.orderstat;

import com.example.orderstatbatch.domain.Order;
import com.example.orderstatbatch.domain.OrderStatistic;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessor implements ItemProcessor<Order, OrderStatistic> {

    @Override
    public OrderStatistic process(Order order) throws Exception {
        // 간단히 Order의 상태를 기반으로 통계 항목 생성
        OrderStatistic stat = new OrderStatistic();
        stat.setOrderStatus(order.getStatus());
        stat.setOrderCount(1);  // 개별 주문은 1건으로 처리
        return stat;
    }
}

