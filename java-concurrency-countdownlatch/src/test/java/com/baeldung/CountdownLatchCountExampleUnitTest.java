package com.baeldung;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CountdownLatchCountExampleUnitTest {

    @Test
    public void whenCountDownLatch_completed() {
        CountdownLatchCountExample ex = new CountdownLatchCountExample(2);
        boolean isCompleted = ex.callTwiceInSameThread();
        assertTrue(isCompleted);
    }
}
