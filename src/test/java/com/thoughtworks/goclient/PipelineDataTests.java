package com.thoughtworks.goclient;

import static junit.framework.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class PipelineDataTests {

    @Test
    public void test() {
        String status = PipelineData.FAIL_TO_CREATE.status();
        assertEquals(PipelineData.FAIL, status);
    }
}
