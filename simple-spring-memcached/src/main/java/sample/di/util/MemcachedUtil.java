package sample.di.util;

import lombok.extern.slf4j.Slf4j;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.utils.AddrUtil;

import java.io.IOException;

@Slf4j
public final class MemcachedUtil {
    private static MemcachedClientBuilder builder = new XMemcachedClientBuilder(
            AddrUtil.getAddresses("localhost:11211"));

    private static MemcachedClient client = getMemcachedClient("localhost:11211");

    public static MemcachedClient getMemcachedClient(String servers) {
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
