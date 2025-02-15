package com.gmail.apach.dima.batch_demo.core.job.import_example.job;

import com.gmail.apach.dima.batch_demo.core.base.job.handler.JobExceptionHandler;
import com.gmail.apach.dima.batch_demo.core.base.job.registry.JobRegistry;
import com.gmail.apach.dima.batch_demo.core.job.import_example.model.ExampleLine;
import com.gmail.apach.dima.batch_demo.core.job.import_example.processor.ExampleProcessor;
import com.gmail.apach.dima.batch_demo.core.job.import_example.reader.ExampleReader;
import com.gmail.apach.dima.batch_demo.core.job.import_example.writer.ExampleWriter;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.inventory.entity.ExampleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class ExampleJob {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final ExampleReader exampleReader;
    private final ExampleProcessor exampleProcessor;
    private final ExampleWriter exampleWriter;
    private final JobExceptionHandler exceptionHandler;
    private final JobExecutionListener commonJobExecutionListener;

    public ItemReader<ExampleLine> chunkReader() {
        return exampleReader;
    }

    public ItemProcessor<ExampleLine, ExampleEntity> chunkProcessor() {
        return exampleProcessor;
    }

    public ItemWriter<ExampleEntity> chunkWriter() {
        return exampleWriter;
    }

    public int chunkSize() {
        return 10;
    }

    @Bean
    public Step chunkStep() {
        return new StepBuilder("STEP", jobRepository)
            .<ExampleLine, ExampleEntity>chunk(chunkSize(), transactionManager)
            .exceptionHandler(exceptionHandler)
            .reader(chunkReader())
            .processor(chunkProcessor())
            .writer(chunkWriter())
            .build();
    }

    @Bean(JobRegistry.EXAMPLE)
    public Job job() {
        return new JobBuilder(JobRegistry.EXAMPLE, jobRepository)
            .start(chunkStep())
            .listener(commonJobExecutionListener)
            .build();
    }
}
