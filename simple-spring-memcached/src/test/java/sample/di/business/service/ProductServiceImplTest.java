package sample.di.business.service;

import lombok.extern.slf4j.Slf4j;
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

@Slf4j
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
    public void updatePriceForGivenProductName() {
        productService.addProduct(new Product("microsoft1", 100));
        productService.addProduct(new Product("microsoft2", 100));
        productService.addProduct(new Product("microsoft3", 100));
        List<String> names = new ArrayList<>(Arrays.asList("microsoft1", "microsoft3"));
        List<Product> result = productService.updatePriceForGivenProductName(names, 500);
        assertEquals(2, result.size());
    }

    @Test
    public void resetPriceForAllProducts() {
        productService.addProduct(new Product("microsoft", 100));
        productService.addProduct(new Product("sony", 100));
        productService.resetPriceForAllProducts();
        List<Product> productList = productService.findAllProducts();
        for (Product product : productList) {
            assertEquals(0, product.getPrice());
        }
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
        this.executeWithMemcachedFlush(productService, () -> {
            productService.addProduct(new Product("microsoft", 100));
            productService.addProduct(new Product("sony", 100));
            productService.findAllProducts();
            productService.resetPriceForAllProducts();
        });
    }

    @Test
    public void testUpdateSingleCache() {
        this.executeWithMemcachedFlush(productService, () -> {
            Product product = new Product("microsoft", 100);
            productService.addProduct(product);
            productService.changeProduct(product.getName(), 500);
            productService.changeProduct(product.getName(), 1000);
            assertEquals(1000, productService.findProduct("microsoft"));
        });
    }

    @Test
    public void testUpdateMultiCache() {
        this.executeWithMemcachedFlush(productService, () -> {
            productService.addProduct(new Product("microsoft1", 100));
            productService.addProduct(new Product("microsoft2", 100));
            productService.addProduct(new Product("microsoft3", 100));
            List<String> names = new ArrayList<>(Arrays.asList("microsoft1", "microsoft3"));
            List<Product> p1 = productService.updatePriceForGivenProductName(names, 1000);
            List<Product> p2 = productService.updatePriceForGivenProductName(names, 500);
            assertEquals(2, p1.size());
            assertEquals(2, p2.size());
        });
    }

    @Test
    public void testInvalidateAssignCache() {
        this.executeWithMemcachedFlush(productService, () -> {
            productService.addProduct(new Product("microsoft", 100));
            productService.addProduct(new Product("sony", 200));
            productService.findAllProducts(); //cached 됨
            List<Product> list = productService.getAllProductsFromMemory(); //cache에서 삭제됨
            assertEquals(1, list.size());
        });
    }

    @Test
    public void testInvalidateSingleCache() {
        this.executeWithMemcachedFlush(productService, () -> {
            productService.addProduct(new Product("microsoft", 100));
            productService.findProduct("microsoft"); //cache됨
            Product product = productService.getProductFromMemory("microsoft"); //cache에서 삭제됨
            assertEquals("microsoft", product.getName());
        });
    }


    @Test
    public void testInvalidateMultiCache() {
        this.executeWithMemcachedFlush(productService, () -> {
            productService.addProduct(new Product("microsoft1", 100));
            productService.addProduct(new Product("microsoft2", 200));
            productService.addProduct(new Product("microsoft3", 300));
            productService.findProduct("microsoft1");
            List<Product> list = productService.getProductGivenProductNameFromMemory(Arrays.asList("microsoft1", "microsoft2"));
            assertEquals(2, list.size());
        });
    }


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