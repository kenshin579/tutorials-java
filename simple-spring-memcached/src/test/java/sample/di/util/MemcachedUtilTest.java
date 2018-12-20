package sample.di.util;

import lombok.extern.slf4j.Slf4j;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeoutException;

import static org.junit.Assert.*;

@Slf4j
public class MemcachedUtilTest {

    MemcachedClient memcachedClient;

    @Before
    public void setUp() throws Exception {
        memcachedClient = MemcachedUtil.getMemcachedClient("localhost:11211");
    }

    @After
    public void tearDown() throws Exception {
        memcachedClient.shutdown();
    }

    @Test
    public void testFlushAll() throws InterruptedException, MemcachedException, TimeoutException {
        memcachedClient.set("a", 0, "test:a");
        assertEquals("test:a", memcachedClient.get("a"));

        memcachedClient.flushAll();
        assertNull(memcachedClient.get("a"));
    }

    @Test
    public void name() {
        //
    }
}