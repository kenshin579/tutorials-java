package sample;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sample.di.business.domain.Product;
import sample.di.business.service.ProductService;
import sample.di.util.MemcachedUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

public class ProductSampleRun {
    private static Consumer<ProductService> execReadThroughSingleCache = (ProductService productService) -> {
        String productName = "hoge";
        productService.addProduct(new Product(productName, 100));
        productService.findProduct(productName);
        productService.findProduct(productName);
    };

//    private static Consumer<ProductService> execUpdateThroughSingleCache = (ProductService productService) -> {
//        String productName = "hoge";
//        productService.addProduct(new Product(productName, 100));
//        productService.findProduct(productName);
//        productService.findProduct(productName);
//    };

    public static void main(String[] args) {
        MemcachedClient memcachedClient = MemcachedUtil.getMemcachedClient("localhost:11211");
        ProductSampleRun productSampleRun = new ProductSampleRun();
        ProductService productService = productSampleRun.initProductService();

        productSampleRun.executeCacheOperation(productService, memcachedClient, execReadThroughSingleCache);
    }


    private void executeCacheOperation(ProductService productService, MemcachedClient memcachedClient, Consumer<ProductService> c){

        System.out.println("-------- START -------");
        try {
            memcachedClient.flushAll();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }

        c.accept(productService);

        System.out.println("-------- END -------");
        System.out.println();
    }

    private ProductService initProductService() {
        @SuppressWarnings("resource")
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "/applicationContext-cache.xml");
        ProductService productService = ctx.getBean(ProductService.class);
        return productService;
    }
}