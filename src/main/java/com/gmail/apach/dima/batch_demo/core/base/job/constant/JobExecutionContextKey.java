package com.gmail.apach.dima.batch_demo.core.base.job.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JobExecutionContextKey {

    public static final String STORED_RESOURCE = "stored-resource";
    public static final String INSERTED_IDS = "inserted-ids";
}
