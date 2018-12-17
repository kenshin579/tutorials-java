package sample.di.business.service;

import sample.di.business.domain.Product;

public interface ProductService {
    Product findProduct(String name);

    int getPrice(String name);

    void addProduct(Product product);
}
