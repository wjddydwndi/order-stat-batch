package com.example.orderstatbatch.job.orderstat;

import com.example.orderstatbatch.domain.OrderSettlement;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class OrderSettleAggregator {

    public OrderSettlement aggregate(List<? extends OrderSettlement> items, LocalDate settlementDate) {

        OrderSettlement summary = new OrderSettlement();
        summary.setSettlementDate(settlementDate);

        int totalCount = 0;
        int completedCount = 0;
        int cancelledCount = 0;

        double totalAmount = 0.0;
        double completedAmount = 0.0;
        double cancelledAmount = 0.0;

        for (OrderSettlement item : items) {
            totalCount += item.getTotalOrderCount();
            completedCount += item.getTotalCompletedCount();
            cancelledCount += item.getTotalCancelledCount();

            totalAmount += item.getTotalAmount();
            completedAmount += item.getCompletedAmount();
            cancelledAmount += item.getCancelledAmount();
        }

        summary.setTotalOrderCount(totalCount);
        summary.setTotalCompletedCount(completedCount);
        summary.setTotalCancelledCount(cancelledCount);

        summary.setTotalAmount(totalAmount);
        summary.setCompletedAmount(completedAmount);
        summary.setCancelledAmount(cancelledAmount);
        summary.setAverageOrderAmount(totalAmount == 0? 0 : totalAmount / totalCount);

        return summary;
    }
}
