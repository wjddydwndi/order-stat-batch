package com.example.orderstatbatch.config;

import com.example.orderstatbatch.domain.Order;
import com.example.orderstatbatch.domain.OrderStatistic;
import com.example.orderstatbatch.job.orderstat.OrderProcessor;
import com.example.orderstatbatch.job.orderstat.OrderReader;
import com.example.orderstatbatch.job.orderstat.OrderReportTasklet;
import com.example.orderstatbatch.job.orderstat.OrderWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchJobConfig {

    private final OrderReader orderReader;
    private final OrderProcessor orderProcessor;
    private final OrderWriter orderWriter;
    private final OrderReportTasklet orderReportTasklet;

    @Bean
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
    }

    @Bean
    public Job orderStatJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("orderStatJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(orderStatStep(jobRepository, transactionManager))
                .next(reportStep(jobRepository, transactionManager))
                .build();
    }

    @Bean
    public Step orderStatStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("orderStatStep", jobRepository)
                .<Order, OrderStatistic>chunk(1000, transactionManager)
                .reader(orderReader)
                .processor(orderProcessor)
                .writer(orderWriter)
                .build();
    }

    @Bean
    public Step reportStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("reportStep", jobRepository)
                .tasklet(orderReportTasklet, transactionManager)
                .build();
    }
}
