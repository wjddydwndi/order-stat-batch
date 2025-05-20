package com.example.orderstatbatch.config;

import com.example.orderstatbatch.domain.Order;
import com.example.orderstatbatch.domain.OrderSettlement;
import com.example.orderstatbatch.job.orderstat.*;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
//@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchJobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    private final OrderReader orderReader;
    private final OrderProcessor orderProcessor;
    private final OrderWriter orderWriter;
    private final OrderReportTasklet orderReportTasklet;

   /* @Bean
    public JobRepository jobRepository(DataSource dataSource, PlatformTransactionManager transactionManager) throws Exception {
        JobRepositoryFactoryBean factoryBean = new JobRepositoryFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTransactionManager(transactionManager);
        factoryBean.setDatabaseType("MYSQL"); // DBMS에 맞게 설정
        return factoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }*/
    @Bean
    public Job orderStatJob() {
        return new JobBuilder("orderStatJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(new JobCompletionNotificationListener())
                .start(orderStatStep())
                .next(reportStep())
                .build();
    }

    @Bean
    public Step orderStatStep() {
        return new StepBuilder("orderStatStep", jobRepository)
                .<Order, OrderSettlement>chunk(1000, transactionManager)
                .reader(orderReader)
                .processor(orderProcessor)
                .writer(orderWriter)
                .build();
    }

    @Bean
    public Step reportStep() {
        return new StepBuilder("reportStep", jobRepository)
                .tasklet(orderReportTasklet, transactionManager)
                .build();
    }
}
