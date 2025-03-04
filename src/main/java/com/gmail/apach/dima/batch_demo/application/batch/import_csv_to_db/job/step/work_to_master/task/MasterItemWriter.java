package com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.job.step.work_to_master.task;

import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.model.MasterModel;
import com.gmail.apach.dima.batch_demo.application.core.job.constant.JobExecutionContextKey;
import com.gmail.apach.dima.batch_demo.port.output.db.MasterTableOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class MasterItemWriter implements ItemWriter<MasterModel>, StepExecutionListener {

    private final MasterTableOutputPort masterTableOutputPort;

    private ExecutionContext executionContext;

    @Override
    public void beforeStep(@NonNull StepExecution stepExecution) {
        final var executionContext = stepExecution.getJobExecution().getExecutionContext();
        executionContext.put(JobExecutionContextKey.INSERTED_IDS, new ArrayList<String>());
        this.executionContext = executionContext;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void write(@NonNull Chunk<? extends MasterModel> chunk) {
        final var inserted = masterTableOutputPort.save((List<MasterModel>) chunk.getItems());
        final var insertedIds = inserted.stream().map(MasterModel::id).toList();

        final var heldIds = (List<String>) this.executionContext.get(JobExecutionContextKey.INSERTED_IDS);
        heldIds.addAll(insertedIds);
    }
}
