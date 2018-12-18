package com.spring.example.util;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * https://stackoverflow.com/questions/35146440/generate-auto-increment-id-in-java
 */
public final class AutoGenerateId {
    private static AtomicLong value = new AtomicLong(0);

    private AutoGenerateId() {
    }

    public static synchronized long incrementAndGet() {

        long result = value.incrementAndGet();
        if (result < 0) {
            value.set(0);
            return 0;
        }
        return result;
    }
}
