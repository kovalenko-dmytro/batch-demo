package com.gmail.apach.dima.batch_demo.infrastructure.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ActiveProfile {

    public static final String LOCAL = "local";
    public static final String DEVELOP = "develop";
    public static final String STAGING = "staging";
    public static final String PRODUCTION = "production";
    public static final String TEST = "test";
    public static final String NOT_TEST = "!test";
    public static final String CLI = "cli";
    public static final String SHELL = "shell";
    public static final String WEB = "web";
}
