package sample.di.business.service;

import sample.di.business.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAllProducts();

    Product findProduct(String name);

    int getPrice(String name);

    void addProduct(Product product);

    void changeProduct(String productName, int overridePrice);

    List<Product> resetPriceForAllProducts();

    List<Integer> getIncrementValue(List<Integer> nums, int incrementValue);

    List<Product> updatePriceForGivenProductName(List<String> nameList, int overridePrice);

    List<Product> getAllProductsFromMemory();

    Product getProductFromMemory(String name);

    List<Product> getProductGivenProductNameFromMemory(List<String> nameList);
}
