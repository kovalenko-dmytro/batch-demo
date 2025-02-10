package com.gmail.apach.dima.batch_demo.example.job;

import com.gmail.apach.dima.batch_demo.core.common.Constant;
import com.gmail.apach.dima.batch_demo.core.config.batch.JobRegistry;
import com.gmail.apach.dima.batch_demo.core.job.ChunkConfigurable;
import com.gmail.apach.dima.batch_demo.core.job.JobConfigurable;
import com.gmail.apach.dima.batch_demo.domain.entity.ExampleEntity;
import com.gmail.apach.dima.batch_demo.example.model.ExampleLine;
import com.gmail.apach.dima.batch_demo.example.processor.ExampleProcessor;
import com.gmail.apach.dima.batch_demo.example.reader.ExampleReader;
import com.gmail.apach.dima.batch_demo.example.writer.ExampleWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
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
public class ExampleJob
    implements JobConfigurable, ChunkConfigurable<ExampleLine, ExampleEntity> {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final ExampleReader exampleReader;
    private final ExampleProcessor exampleProcessor;
    private final ExampleWriter exampleWriter;

    @Override
    public ItemReader<ExampleLine> chunkReader() {
        return exampleReader;
    }

    @Override
    public ItemProcessor<ExampleLine, ExampleEntity> chunkProcessor() {
        return exampleProcessor;
    }

    @Override
    public ItemWriter<ExampleEntity> chunkWriter() {
        return exampleWriter;
    }

    @Override
    public int chunkSize() {
        return 10;
    }

    @Override
    public String chunkStepName() {
        return JobRegistry.EXAMPLE.concat(Constant.CHUNK_STEP_SUFFIX);
    }

    @Bean
    @Override
    public Step chunkStep() {
        return new StepBuilder(chunkStepName(), jobRepository)
            .<ExampleLine, ExampleEntity>chunk(chunkSize(), transactionManager)
            .reader(chunkReader())
            .processor(chunkProcessor())
            .writer(chunkWriter())
            .build();
    }

    @Bean(JobRegistry.EXAMPLE)
    @Override
    public Job job() {
        return new JobBuilder(JobRegistry.EXAMPLE, jobRepository)
            .start(chunkStep())
            .build();
    }

}
