package org.chernov.managerapp.repository;

import org.chernov.managerapp.dto.UpdateProductRequest;
import org.chernov.managerapp.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();
    Product save(Product product);
    Optional<Product> findProductById(Integer id);
    void deleteProductById(Integer id);

}
