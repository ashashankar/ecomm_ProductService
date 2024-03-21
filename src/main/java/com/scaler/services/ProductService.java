package com.scaler.services;

import com.scaler.models.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(Long Id);

    List<Product> getAllProducts();

    Product updateProduct(Long Id);
    Product replaceProduct(Long Id);
    Product createProduct(Long Id);

    void deleteProduct(Long Id);
}
