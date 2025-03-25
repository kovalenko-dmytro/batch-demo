package com.gmail.apach.dima.batch_demo.infrastructure.input.cli.common.mapper;

import com.gmail.apach.dima.batch_demo.application.core.job.model.RequestParameter;
import com.gmail.apach.dima.batch_demo.application.core.job.model.RequestParameters;
import com.gmail.apach.dima.batch_demo.common.constant.Delimiter;
import org.springframework.boot.ApplicationArguments;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

@Component
public class CliParametersMapper {

    public RequestParameters toRequestParameters(@NonNull ApplicationArguments args) {
        final var result = new HashMap<RequestParameter, String>();
        final var sourceArgs = args.getSourceArgs();
        for (var sourceArg : sourceArgs) {
            final var keyValue = sourceArg.split(Delimiter.EQUAL);
            result.put(
                RequestParameter.from(keyValue[0]),
                keyValue.length == 2 ? keyValue[1] : Delimiter.EMPTY
            );
        }
        result.putIfAbsent(RequestParameter.JOB_EXECUTION_MARKER, UUID.randomUUID().toString());
        return new RequestParameters(result);
    }
}
