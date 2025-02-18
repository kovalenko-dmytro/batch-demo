package com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.work_to_master.listener;

import com.gmail.apach.dima.batch_demo.core.base.job.constant.JobExecutionContextKey;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class InitRollbackHolderStepExecutionListener implements StepExecutionListener {

    @Override
    public void beforeStep(@NonNull StepExecution stepExecution) {
        stepExecution
            .getJobExecution()
            .getExecutionContext()
            .put(JobExecutionContextKey.INSERTED_IDS, new ArrayList<String>());
    }
}
