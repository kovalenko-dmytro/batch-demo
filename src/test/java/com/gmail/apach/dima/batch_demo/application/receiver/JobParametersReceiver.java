package com.gmail.apach.dima.batch_demo.application.receiver;

import com.gmail.apach.dima.batch_demo.application.core.job.model.RequestParameter;
import com.gmail.apach.dima.batch_demo.application.core.job.registry.JobRegistry;
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
    public static JobParameters importExcelToCsv(String resourceKey) {
        final var paramsBuilder = new JobParametersBuilder();
        paramsBuilder.addString(RequestParameter.JOB_NAME.getArg(), JobRegistry.IMPORT_EXCEL_TO_CSV);
        paramsBuilder.addString(RequestParameter.FILE_STORAGE_RESOURCE.getArg(), resourceKey);
        return paramsBuilder.toJobParameters();
    }

}
