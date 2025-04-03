package com.gmail.apach.dima.batch_demo.common.validator.policy.implementation;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class FileStorageResourceNoneBlankPolicyTest {

    private static final String FILE_RESOURCE = "file-resource";

    @InjectMocks
    private FileStorageResourceNoneBlankPolicy policy;

    @Test
    void satisfy_try() {
        assertTrue(policy.satisfy(FILE_RESOURCE));
    }

    @Test
    void satisfy_false() {
        assertFalse(policy.satisfy(StringUtils.EMPTY));
    }
}