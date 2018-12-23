package sample.di.dataaccess;

import com.google.code.ssm.api.*;
import org.springframework.stereotype.Repository;
import sample.di.business.domain.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ProductDaoImpl implements ProductDao {

    // RDB의 대체
    private Map<String, Product> storage = new HashMap<String, Product>();

    @ReadThroughAssignCache(namespace = "area", assignedKey = "all")
    public List<Product> findAllProducts() {
        slowly(); // 고의로 지연시킴
        return storage.values().stream().collect(Collectors.toList());
    }

    @ReadThroughSingleCache(namespace = "area")
    public Product findProduct(@ParameterValueKeyProvider String name) {
        slowly(); // 고의로 지연시킴
        return storage.get(name);
    }

    @ReadThroughMultiCache(namespace = "area")
    public List<Integer> getIncrementValue(@ParameterValueKeyProvider List<Integer> nums, int incrementValue) {
        slowly(); // 고의로 지연시킴
        return nums.stream().map(x -> x + incrementValue).collect(Collectors.toList());
    }

    @ReadThroughSingleCache(namespace = "area")
    public int getPrice(@ParameterValueKeyProvider String name) {
        slowly(); // 고의로 지연시킴
        return storage.get(name).getPrice();
    }

    public void addProduct(@ParameterValueKeyProvider Product product) {
        storage.put(product.getName(), product);
    }

    @ReturnDataUpdateContent
    @UpdateAssignCache(namespace = "area", assignedKey = "all")
    public List<Product> resetPriceForAllProducts() {
        slowly(); // 고의로 지연시킴
        return storage.values().stream()
                .map(product -> {
                    product.setPrice(0);
                    return product;
                }).collect(Collectors.toList());
    }

    @UpdateSingleCache(namespace = "area")
    public void changeProduct(@ParameterValueKeyProvider String productName, @ParameterDataUpdateContent int overridePrice) {
        slowly(); // 고의로 지연시킴
        Product product = storage.get(productName);
        product.setPrice(overridePrice);
        storage.replace(productName, product);
    }

    @ReturnDataUpdateContent
    @UpdateMultiCache(namespace = "area")
    public List<Product> updatePriceForGivenProductName(@ParameterValueKeyProvider List<String> nameList, @ParameterDataUpdateContent int overridePrice) {
        slowly(); // 고의로 지연시킴
        List<Product> result = new ArrayList<>();
        Product product;

        for (String name : nameList) {
            product = storage.get(name);
            product.setPrice(overridePrice);
            result.add(product);
        }

        return result;
    }

    @InvalidateAssignCache(namespace = "area", assignedKey = "all")
    public List<Product> getAllProductsFromMemory() {
        slowly(); // 고의로 지연시킴
        return storage.values().stream().collect(Collectors.toList());
    }

    @InvalidateSingleCache(namespace = "area")
    public Product getProductFromMemory(@ParameterValueKeyProvider String name) {
        slowly(); // 고의로 지연시킴
        return storage.get(name);
    }

//    @InvalidateMultiCache(namespace = "area")
//    public List<Integer> getIncrementValue(@ParameterValueKeyProvider List<Integer> nums, int incrementValue) {
//        slowly(); // 고의로 지연시킴
//        return nums.stream().map(x -> x + incrementValue).collect(Collectors.toList());
//    }

    private void slowly() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}