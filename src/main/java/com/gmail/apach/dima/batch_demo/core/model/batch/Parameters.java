package com.gmail.apach.dima.batch_demo.core.model.batch;

import com.gmail.apach.dima.batch_demo.core.common.Constant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.boot.ApplicationArguments;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Parameters {

    private final Map<Parameter, String> properties;

    public Parameters(ApplicationArguments args) {
        this.properties = argsToProperties(args);
    }

    private static Map<Parameter, String> argsToProperties(ApplicationArguments args) {
        final var result = new HashMap<Parameter, String>();
        final var sourceArgs = args.getSourceArgs();
        for (var sourceArg : sourceArgs) {
            final var argPair = sourceArg.split(Constant.EQUAL);
            result.put(Parameter.from(argPair[0]), argPair.length == 2 ? argPair[1] : StringUtils.EMPTY);
        }
        return result;
    }

    public String get(Parameter parameter) {
        return Optional
            .ofNullable(properties.get(parameter))
            .orElse(StringUtils.EMPTY);
    }

    public JobParametersBuilder fromProperties() {
        final var builder = new JobParametersBuilder();
        properties.forEach((name, value) -> builder.addString(Parameter.from(name), value));
        return builder;
    }
}
