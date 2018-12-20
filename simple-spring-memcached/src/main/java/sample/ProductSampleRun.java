package sample;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sample.di.business.domain.Product;
import sample.di.business.service.ProductService;
import sample.di.util.MemcachedUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class ProductSampleRun {
    public static void main(String[] args) {
        ProductSampleRun productSampleRun = new ProductSampleRun();
        ProductService productService = productSampleRun.initProductService();

        productService.addProduct(new Product("microsoft", 100));
        productService.addProduct(new Product("sony", 100));
        productService.findProduct("sony");
        productService.findProduct("sony");
    }

    private ProductService initProductService() {
        @SuppressWarnings("resource")
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "/applicationContext-cache.xml");
        ProductService productService = ctx.getBean(ProductService.class);
        return productService;
    }
}