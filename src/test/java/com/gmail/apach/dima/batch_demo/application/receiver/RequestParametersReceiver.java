package com.gmail.apach.dima.batch_demo.application.receiver;

import com.gmail.apach.dima.batch_demo.application.core.job.model.RequestParameter;
import com.gmail.apach.dima.batch_demo.application.core.job.model.RequestParameters;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RequestParametersReceiver {

    public static RequestParameters parameters() {
        return new RequestParameters(Map.of(RequestParameter.JOB_NAME, "job-name"));
    }
}
