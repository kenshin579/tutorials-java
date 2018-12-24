package sample.di.business.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import sample.di.business.domain.Product;
import sample.di.dataaccess.ProductDao;

import java.util.List;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    public List<Product> findAllProducts() {
        // 측정 시작
        StopWatch sw = new StopWatch();
        sw.start();

        List<Product> value = productDao.findAllProducts();
        // 측정 종료
        sw.stop();

        System.out.format("Seconds=%1$s, value=%2$s%n",
                sw.getTotalTimeSeconds(), value);
        return value;
    }

    public Product findProduct(String name) {
        log.debug("findProduct : name : {}", name);
        // 측정 시작
        StopWatch sw = new StopWatch();
        sw.start();

        Product product = productDao.findProduct(name);

        // 측정 종료
        sw.stop();

        System.out.format("Seconds=%1$s, value=%2$s%n",
                sw.getTotalTimeSeconds(), product);

        return product;
    }

    public int getPrice(String name) {
        log.debug("getPrice : name : {}", name);
        // 측정 시작
        StopWatch sw = new StopWatch();
        sw.start();

        int price = productDao.getPrice(name);

        // 측정 종료
        sw.stop();

        System.out.format("Seconds=%1$s, value=%2$s%n",
                sw.getTotalTimeSeconds(), price);
        return price;
    }

    public void addProduct(Product product) {
        log.debug("addProduct : Product : {}", product);
        // 측정 시작
        StopWatch sw = new StopWatch();
        sw.start();

        productDao.addProduct(product);

        // 측정 종료
        sw.stop();

        System.out.format("Seconds=%1$s, value=%2$s%n",
                sw.getTotalTimeSeconds(), product);
    }

    public void changeProduct(String productName, int overridePrice) {
        log.debug("changeProduct : productName : {} : overridePrice : {}", overridePrice);
        // 측정 시작
        StopWatch sw = new StopWatch();
        sw.start();

        productDao.changeProduct(productName, overridePrice);

        // 측정 종료
        sw.stop();
        System.out.format("Seconds=%1$s, value=%2$s%n",
                sw.getTotalTimeSeconds(), productName);
    }

    public List<Product> resetPriceForAllProducts() {
        log.debug("resetPriceForAllProducts");
        // 측정 시작
        StopWatch sw = new StopWatch();
        sw.start();

        List<Product> value = productDao.resetPriceForAllProducts();
        // 측정 종료
        sw.stop();

        System.out.format("Seconds=%1$s, value=%2$s%n",
                sw.getTotalTimeSeconds(), value);
        return value;
    }

    @Override
    public List<Integer> getIncrementValue(List<Integer> nums, int incrementValue) {
        log.debug("getIncrementValue : nums : {}", nums);
        // 측정 시작
        StopWatch sw = new StopWatch();
        sw.start();

        List<Integer> value = productDao.getIncrementValue(nums, incrementValue);

        // 측정 종료
        sw.stop();

        System.out.format("Seconds=%1$s, value=%2$s%n",
                sw.getTotalTimeSeconds(), value);
        return value;
    }

    public List<Product> updatePriceForGivenProductName(List<String> nameList, int overridePrice) {
        log.debug("updatePriceForGivenProductName : nameList : {}", nameList);
        // 측정 시작
        StopWatch sw = new StopWatch();
        sw.start();

        List<Product> value = productDao.updatePriceForGivenProductName(nameList, overridePrice);

        // 측정 종료
        sw.stop();

        System.out.format("Seconds=%1$s, value=%2$s%n",
                sw.getTotalTimeSeconds(), value);
        return value;
    }

    @Override
    public List<Product> getAllProductsFromMemory() {
        log.debug("getAllProductsFromMemory");
        // 측정 시작
        StopWatch sw = new StopWatch();
        sw.start();

        List<Product> value = productDao.getAllProductsFromMemory();

        // 측정 종료
        sw.stop();

        System.out.format("Seconds=%1$s, value=%2$s%n",
                sw.getTotalTimeSeconds(), value);
        return value;
    }

    @Override
    public Product getProductFromMemory(String name) {
        log.debug("getProductFromMemory : name : {}", name);
        // 측정 시작
        StopWatch sw = new StopWatch();
        sw.start();

        Product product = productDao.getProductFromMemory(name);

        // 측정 종료
        sw.stop();

        System.out.format("Seconds=%1$s, value=%2$s%n",
                sw.getTotalTimeSeconds(), product);

        return product;
    }

    @Override
    public List<Product> getProductGivenProductNameFromMemory(List<String> nameList) {
        log.debug("getProductGivenProductNameFromMemory : nameList : {}", nameList);
        // 측정 시작
        StopWatch sw = new StopWatch();
        sw.start();

        List<Product> value = productDao.getProductGivenProductNameFromMemory(nameList);

        // 측정 종료
        sw.stop();

        System.out.format("Seconds=%1$s, value=%2$s%n",
                sw.getTotalTimeSeconds(), value);

        return value;
    }
}