package sample.di.business.service;

import net.rubyeye.xmemcached.exception.MemcachedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sample.di.business.domain.Product;
import sample.di.util.MemcachedUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-cache.xml")
public class ProductServiceImplTest {

    @Autowired
    ProductService productService;

    @Test
    public void getPrice() {
        productService.addProduct(new Product("microsoft", 100));
        assertEquals(100, productService.getPrice("microsoft"));
    }

    @Test
    public void testReadThroughAssignCache() {
        this.executeWithMemcachedFlush(productService, () -> {
            productService.addProduct(new Product("microsoft", 100));
            productService.addProduct(new Product("sony", 100));
            productService.findAllProducts();
            productService.findAllProducts();
        });
    }

    @Test
    public void testReadThroughSingleCache() {
        this.executeWithMemcachedFlush(productService, () -> {
            productService.addProduct(new Product("microsoft", 100));
            productService.addProduct(new Product("sony", 100));
            productService.findProduct("microsoft");
            productService.findProduct("microsoft");
        });
    }

    @Test
    public void testReadThroughMultiCache() {
        this.executeWithMemcachedFlush(productService, () -> {
            List<Integer> nums = new ArrayList<Integer>(Arrays.asList(2, 3, 4, 5));
            productService.getIncrementValue(nums, 4);
            productService.getIncrementValue(nums, 1);
        });
    }

    @Test
    public void testUpdateAssignCache() {

    }

//    * @UpdateAssignCache
//* @UpdateSingleCache
//* @UpdateMultiCache
//    Invalidate
//* @InvalidateAssignCache
//* @InvalidateSingleCache
//* @InvalidateMultiCache


    private void executeWithMemcachedFlush(ProductService productService, Runnable r) {

        System.out.println("---------------- START ----------------");
        try {
            System.out.println("flushing...");
            MemcachedUtil.getMemcachedClient("localhost:11211").flushAll();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }

        r.run();

        System.out.println("---------------- END ----------------");
        System.out.println();
    }


}