package com.example.orderstatbatch.job.orderstat;

import com.example.orderstatbatch.domain.Order;
import com.example.orderstatbatch.domain.OrderSettlement;
import com.example.orderstatbatch.enums.StatusEnums;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class OrderProcessor implements ItemProcessor<Order, OrderSettlement> {

    @Override
    public OrderSettlement process(Order order) throws Exception {
        OrderSettlement settlement = new OrderSettlement();
        settlement.setSettlementDate(LocalDate.now());
        settlement.setTotalOrderCount(1);
        settlement.setTotalAmount(order.getAmount());

        if (StatusEnums.code_order_status_completed.getCode().equals(order.getStatus())) {
            settlement.setTotalCompletedCount(1);
            settlement.setCompletedAmount(order.getAmount());
        } else if (StatusEnums.code_order_status_cancelled.getCode().equals(order.getStatus())) {
            settlement.setTotalCancelledCount(1);
            settlement.setCancelledAmount(order.getAmount());
        }
        settlement.setAverageOrderAmount(order.getAmount());

        return settlement;
    }
}
