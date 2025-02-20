package com.gmail.apach.dima.batch_demo.infrastructure.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public enum RunMode {

    CLI("cli"),
    SHELL("shell"),
    WEB("web");

    public static final Set<String> modes =
        Arrays.stream(RunMode.values()).map(RunMode::getMode).collect(Collectors.toSet());

    private final String mode;

    public static RunMode from(String value) {
        return Arrays.stream(RunMode.values())
            .filter(mode -> mode.getMode().equalsIgnoreCase(value))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("run mode: <" + value + "> has not implemented"));
    }
}
