package org.chernov.catalogueservice.controller;


import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.chernov.catalogueservice.dto.UpdateProductRequest;
import org.chernov.catalogueservice.entity.Product;
import org.chernov.catalogueservice.service.ProductService;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Optional;


@RestController
@RequestMapping("catalogue-api/products/{productId:\\d+}")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @ModelAttribute("product")
    public Optional<Product> getProduct(@PathVariable("productId") int productId) {
        return productService.findProduct(productId);
    }
    @GetMapping
    public Product findProduct(@ModelAttribute("product") Product product) {
        return product;
    }

    @PatchMapping
    public ResponseEntity<?> updateProduct(@PathVariable("productId") int productId,
                                              @Valid @RequestBody UpdateProductRequest updateProductRequest, BindingResult bindingResult,
                                           Locale locale,
                                           BadRequestControllerAdvice badRequestControllerAdvice) {
        if(bindingResult.hasErrors()){
            return badRequestControllerAdvice.handleBindException(bindingResult, locale);

        }else{
            productService.updateProduct(productId, updateProductRequest);
            return ResponseEntity.noContent().build(); // noContent указывает на то, что ответ не содержит данных
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProduct(@PathVariable("productId") int productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }




}
