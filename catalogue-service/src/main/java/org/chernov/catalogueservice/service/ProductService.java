package org.chernov.catalogueservice.service;

import org.chernov.catalogueservice.dto.CreateProductRequest;
import org.chernov.catalogueservice.dto.UpdateProductRequest;
import org.chernov.catalogueservice.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAllProducts();
    Product createProduct(CreateProductRequest request);
    Optional<Product> findProduct(Integer id);

    void updateProduct(Integer id, UpdateProductRequest updateProductRequest);
    void deleteProduct(int productId);
}
