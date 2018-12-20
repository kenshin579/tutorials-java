package sample.di.util;

import lombok.extern.slf4j.Slf4j;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;
import sample.di.business.service.ProductService;
import sample.di.business.service.ProductServiceImpl;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

@Slf4j
public final class MemcachedUtil {
    static final String servers = "localhost:11211";

    private static MemcachedClientBuilder builder = new XMemcachedClientBuilder(
            AddrUtil.getAddresses(servers));

    public static MemcachedClient getMemcachedClient(String servers) {
        try {
            MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(servers));
            return builder.build();
        } catch (IOException e) {
            log.error("Create MemcachedClient fail", e);
        }
        return null;
    }

    public static MemcachedClient getMemcachedClient() {
        try {
            // use text protocol by default
            MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(servers));
            return builder.build();
        } catch (IOException e) {
            log.error("Create MemcachedClient fail", e);
        }
        return null;
    }
}
