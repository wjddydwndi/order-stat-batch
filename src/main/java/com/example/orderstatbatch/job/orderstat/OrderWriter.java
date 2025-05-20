package com.example.orderstatbatch.job.orderstat;

import com.example.orderstatbatch.domain.OrderSettlement;
import com.example.orderstatbatch.mapper.OrderSettlementMapper;
import com.example.orderstatbatch.service.OrderSettlementService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderWriter implements ItemWriter<OrderSettlement> {

    private final OrderSettleAggregator orderSettleAggregator;

    private final OrderSettlementService orderSettlementService;

    @Override
    public void write(Chunk<? extends OrderSettlement> chunk) throws Exception {
        List<? extends OrderSettlement> items = chunk.getItems();

        if (items == null || items.isEmpty()) {
            return;
        }

        LocalDate settlementDate = LocalDate.now();
        OrderSettlement summary = orderSettleAggregator.aggregate(items, settlementDate);

        orderSettlementService.insertSettlement(summary);
    }
}
