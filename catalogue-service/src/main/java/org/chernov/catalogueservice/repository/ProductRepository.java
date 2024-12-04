package org.chernov.catalogueservice.repository;

import org.chernov.catalogueservice.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();
    Product save(Product product);
    Optional<Product> findProductById(Integer id);
    void deleteProductById(Integer id);

}
