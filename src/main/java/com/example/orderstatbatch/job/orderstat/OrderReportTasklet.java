package com.example.orderstatbatch.job.orderstat;

import com.example.orderstatbatch.domain.OrderSettlement;
import com.example.orderstatbatch.mapper.OrderSettlementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderReportTasklet implements Tasklet {

    private final OrderSettlementMapper orderSettlementMapper;


    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        // 최근 생성된 정산 결과를 DB에서 조회
        OrderSettlement latestSettlement = orderSettlementMapper.selectLatestSettlement();

        // 통계 출력
        System.out.println("=== 배치 작업 결과 ===");
        System.out.println("정산일자: " + latestSettlement.getSettlementDate());
        System.out.println("총 주문 수: " + latestSettlement.getTotalOrderCount());
        System.out.println("완료된 주문 수: " + latestSettlement.getTotalCompletedCount());
        System.out.println("취소된 주문 수: " + latestSettlement.getTotalCancelledCount());
        System.out.println("총 금액: " + latestSettlement.getTotalAmount());
        System.out.println("완료된 금액: " + latestSettlement.getCompletedAmount());
        System.out.println("취소된 금액: " + latestSettlement.getCancelledAmount());
        System.out.println("평균 주문 금액: " + latestSettlement.getAverageOrderAmount());

        return RepeatStatus.FINISHED;
    }
}
