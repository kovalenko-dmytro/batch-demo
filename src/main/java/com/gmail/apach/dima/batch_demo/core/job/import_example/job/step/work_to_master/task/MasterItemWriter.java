package com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.work_to_master.task;

import com.gmail.apach.dima.batch_demo.application.output.db.MasterExampleOutputPort;
import com.gmail.apach.dima.batch_demo.core.base.job.constant.JobExecutionContextKey;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.example.entity.MasterExampleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class MasterItemWriter
    implements ItemWriter<MasterExampleEntity>, ChunkListener {

    private final MasterExampleOutputPort masterExampleOutputPort;

    private List<String> insertedIds;

    @Override
    public void write(@NonNull Chunk<? extends MasterExampleEntity> chunk) {
        final var items = chunk.getItems().stream()
            .map(item -> (MasterExampleEntity) item)
            .toList();
        final var inserted = masterExampleOutputPort.save(items);
        insertedIds.addAll(inserted.stream().map(MasterExampleEntity::getId).toList());
    }

    @Override
    @SuppressWarnings("unchecked")
    public void beforeChunk(@NonNull ChunkContext context) {
        this.insertedIds = (List<String>) context
            .getStepContext()
            .getJobExecutionContext()
            .get(JobExecutionContextKey.INSERTED_IDS);
    }

    /*@Override
    public void afterChunk(@NonNull ChunkContext context) {
        context
            .getStepContext()
            .getJobExecutionContext()
            .replace(JobExecutionContextKey.INSERTED_IDS, insertedIds);
    }

    @Override
    public void afterChunkError(@NonNull ChunkContext context) {
        context
            .getStepContext()
            .getJobExecutionContext()
            .replace(JobExecutionContextKey.INSERTED_IDS, insertedIds);
    }*/
}
