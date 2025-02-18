package com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.work_to_master.listener;

import com.gmail.apach.dima.batch_demo.core.base.job.constant.JobExecutionContextKey;
import org.springframework.batch.core.listener.ExecutionContextPromotionListener;
import org.springframework.stereotype.Component;

@Component
public class JobPromotionListener extends ExecutionContextPromotionListener {

    @Override
    public void afterPropertiesSet() throws Exception {
        super.setKeys(new String[] {JobExecutionContextKey.INSERTED_IDS});
        super.afterPropertiesSet();
    }
}
