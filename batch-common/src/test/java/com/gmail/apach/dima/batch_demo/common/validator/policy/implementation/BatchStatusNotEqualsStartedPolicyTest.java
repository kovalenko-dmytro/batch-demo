package com.gmail.apach.dima.batch_demo.common.validator.policy.implementation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.BatchStatus;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class BatchStatusNotEqualsStartedPolicyTest {

    @InjectMocks
    private BatchStatusNotEqualsStartedPolicy policy;

    @Test
    void satisfy_try() {
        assertTrue(policy.satisfy(BatchStatus.COMPLETED));
        assertTrue(policy.satisfy(BatchStatus.FAILED));
        assertTrue(policy.satisfy(BatchStatus.ABANDONED));
        assertTrue(policy.satisfy(BatchStatus.STOPPED));
        assertTrue(policy.satisfy(BatchStatus.STOPPING));
        assertTrue(policy.satisfy(BatchStatus.UNKNOWN));

    }

    @Test
    void satisfy_false() {
        assertFalse(policy.satisfy(BatchStatus.STARTING));
        assertFalse(policy.satisfy(BatchStatus.STARTED));
    }
}