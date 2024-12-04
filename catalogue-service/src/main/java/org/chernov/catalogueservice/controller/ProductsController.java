package org.chernov.catalogueservice.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.chernov.catalogueservice.dto.CreateProductRequest;
import org.chernov.catalogueservice.entity.Product;
import org.chernov.catalogueservice.service.ProductService;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("catalogue-api/products")
public class ProductsController {

    private final ProductService productService;
    private final MessageSource messageSource;

    @GetMapping
    public List<Product> findProducts(){
        return productService.findAllProducts();
    }

    @PostMapping()
    public ResponseEntity<?> createProduct(@Valid @RequestBody CreateProductRequest createProductRequest,
                                                 BindingResult bindingResult, UriComponentsBuilder uriBuilder, Locale locale){
        if(bindingResult.hasErrors()){
            ProblemDetail problemDetail = ProblemDetail
                    .forStatusAndDetail(HttpStatus.BAD_REQUEST, messageSource.getMessage("errors.400.title", new Object[0], "errors.400.title", locale));

            problemDetail.setProperty("errors", bindingResult.getAllErrors().stream().map(MessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList()));

            return ResponseEntity.badRequest()
                    .body(problemDetail);

        }else{
            Product product = productService.createProduct(createProductRequest);

            Map<String, Object> uriVariables = new HashMap<>();
            uriVariables.put("productId", product.getId());

            return ResponseEntity
                    .created(uriBuilder
                            .replacePath("/catalogue-api/products/{productId}")
                            .build(uriVariables))
                    .body(product);
        }




    }


}
