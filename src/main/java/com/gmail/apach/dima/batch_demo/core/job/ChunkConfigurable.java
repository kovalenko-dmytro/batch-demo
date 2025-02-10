package com.gmail.apach.dima.batch_demo.core.job;

import org.springframework.batch.core.Step;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;

public interface ChunkConfigurable<I, O> {

    ItemReader<I> chunkReader();

    ItemProcessor<I, O> chunkProcessor();

    ItemWriter<O> chunkWriter();

    int chunkSize();

    String chunkStepName();

    Step chunkStep();
}
