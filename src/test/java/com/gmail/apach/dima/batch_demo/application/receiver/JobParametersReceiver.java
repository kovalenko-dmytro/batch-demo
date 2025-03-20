package com.gmail.apach.dima.batch_demo.application.receiver;

import com.gmail.apach.dima.batch_demo.application.core.job.constant.JobName;
import com.gmail.apach.dima.batch_demo.application.core.job.model.RequestParameter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JobParametersReceiver {

    public static JobParameters importCsvToD(String resourceKey) {
        final var paramsBuilder = new JobParametersBuilder();
        paramsBuilder.addString(RequestParameter.JOB_NAME.getName(), JobName.IMPORT_CSV_TO_DB);
        paramsBuilder.addString(RequestParameter.FILE_STORAGE_RESOURCE.getName(), resourceKey);
        return paramsBuilder.toJobParameters();
    }

    public static JobParameters importXmlZipToExcel(String resourceKey) {
        final var paramsBuilder = new JobParametersBuilder();
        paramsBuilder.addString(RequestParameter.JOB_NAME.getName(), JobName.IMPORT_XML_ZIP_TO_EXCEL);
        paramsBuilder.addString(RequestParameter.FILE_STORAGE_RESOURCE.getName(), resourceKey);
        return paramsBuilder.toJobParameters();
    }

    public static JobParameters importExcelToCsv(String resourceKey) {
        final var paramsBuilder = new JobParametersBuilder();
        paramsBuilder.addString(RequestParameter.JOB_NAME.getName(), JobName.IMPORT_EXCEL_TO_CSV);
        paramsBuilder.addString(RequestParameter.FILE_STORAGE_RESOURCE.getName(), resourceKey);
        return paramsBuilder.toJobParameters();
    }
}
