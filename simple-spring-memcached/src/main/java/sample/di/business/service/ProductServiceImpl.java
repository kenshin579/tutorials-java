package sample.di.business.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import sample.di.business.domain.Product;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

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
        productDao.addProduct(product);
    }
}