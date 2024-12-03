package org.chernov.managerapp.service;


import lombok.RequiredArgsConstructor;
import org.chernov.managerapp.dto.CreateProductRequest;
import org.chernov.managerapp.dto.UpdateProductRequest;
import org.chernov.managerapp.entity.Product;
import org.chernov.managerapp.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultProductService implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> findAllProducts() {
        return this.productRepository.findAll();
    }

    public Product createProduct(CreateProductRequest request) {
        Product product = Product.builder()
                .id(null)
                .title(request.getTitle())
                .details(request.getDetails())
                .build();
        return productRepository.save(product);
    }

    public Optional<Product> findProduct(Integer id) {
        return productRepository.findProductById(id);
    }

    @Override
    public void updateProduct(Integer id, UpdateProductRequest updateProductRequest) {

        productRepository.findProductById(id)
                .ifPresent(product ->{
                    product.setTitle(updateProductRequest.getTitle());
                    product.setDetails(updateProductRequest.getDetails());
                });

    }

    public void deleteProduct(Product product) {
        productRepository.deleteProductById(product.getId());
    }
}
