package com.example.orderstatbatch.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderStatScheduler {

    private final JobLauncher jobLauncher;
    private final Job orderStatJob;

    @Scheduled(cron = "10 * * * * *")
    public void run() {
        String runTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

        try {
            JobParameters parameters = new JobParametersBuilder().addString("runTime", runTime).toJobParameters();

            log.info("배치 실행 : {}", runTime);
            jobLauncher.run(orderStatJob, parameters);
            log.info("배치 종료 : {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        } catch (Exception e) {
            log.error("정산 배치 실행 중 오류 발생, e : {}", e);
        }
    }
}
