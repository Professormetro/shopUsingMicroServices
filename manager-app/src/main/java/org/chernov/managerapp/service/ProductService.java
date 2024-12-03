package org.chernov.managerapp.service;

import org.chernov.managerapp.dto.CreateProductRequest;
import org.chernov.managerapp.dto.UpdateProductRequest;
import org.chernov.managerapp.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAllProducts();
    Product createProduct(CreateProductRequest request);
    Optional<Product> findProduct(Integer id);

    void updateProduct(Integer id, UpdateProductRequest updateProductRequest);
    void deleteProduct(Product product);
}
