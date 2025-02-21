package com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.cli.mapper;

import com.gmail.apach.dima.batch_demo.core.base.common.constant.Delimiter;
import com.gmail.apach.dima.batch_demo.core.base.model.job.RequestParameter;
import com.gmail.apach.dima.batch_demo.core.base.model.job.RequestParameters;
import org.springframework.boot.ApplicationArguments;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

@Component
public class CliMapper {

    public RequestParameters toParameters(@NonNull ApplicationArguments args) {
        final var result = new HashMap<RequestParameter, String>();
        final var sourceArgs = args.getSourceArgs();
        for (var sourceArg : sourceArgs) {
            final var argPair = sourceArg.split(Delimiter.EQUAL);
            result.put(
                RequestParameter.from(argPair[0]),
                argPair.length == 2 ? argPair[1] : Delimiter.EMPTY
            );
        }
        if (!result.containsKey(RequestParameter.JOB_EXEC_MARK)) {
            result.put(RequestParameter.JOB_EXEC_MARK, UUID.randomUUID().toString());
        }
        return new RequestParameters(result);
    }
}
