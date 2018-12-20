package sample.di.business.service;

import sample.di.business.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAllProducts();

    Product findProduct(String name);

    int getPrice(String name);

    void addProduct(Product product);

    List<Integer> getIncrementValue(List<Integer> nums, int incrementValue);
}
