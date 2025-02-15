package com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.cli.mapper;

import com.gmail.apach.dima.batch_demo.core.base.common.constant.Delimiter;
import com.gmail.apach.dima.batch_demo.core.base.model.job.Parameter;
import com.gmail.apach.dima.batch_demo.core.base.model.job.RequestParameters;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class CliMapper {

    public RequestParameters toParameters(@NonNull ApplicationArguments args) {
        final var result = new HashMap<Parameter, String>();
        final var sourceArgs = args.getSourceArgs();
        for (var sourceArg : sourceArgs) {
            final var argPair = sourceArg.split(Delimiter.EQUAL);
            result.put(
                Parameter.from(argPair[0]),
                argPair.length == 2 ? argPair[1] : StringUtils.EMPTY
            );
        }
        return new RequestParameters(result);
    }
}
