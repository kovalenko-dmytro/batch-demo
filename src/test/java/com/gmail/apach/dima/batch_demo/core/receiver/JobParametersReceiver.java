package com.gmail.apach.dima.batch_demo.core.receiver;

import com.gmail.apach.dima.batch_demo.core.base.job.registry.JobRegistry;
import com.gmail.apach.dima.batch_demo.core.base.model.job.RequestParameter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JobParametersReceiver {

    public static JobParameters importCsvToD(String resourceKey) {
        final var paramsBuilder = new JobParametersBuilder();
        paramsBuilder.addString(RequestParameter.JOB_NAME.getArg(), JobRegistry.IMPORT_CSV_TO_DB);
        paramsBuilder.addString(RequestParameter.FILE_STORAGE_RESOURCE.getArg(), resourceKey);
        return paramsBuilder.toJobParameters();
    }
}
