package org.chernov.managerapp.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.chernov.managerapp.dto.CreateProductRequest;
import org.chernov.managerapp.entity.Product;
import org.chernov.managerapp.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("catalogue/products")
public class ProductsController {

    private final ProductService productService;



    @GetMapping("/list")
    public String getProductsList(Model model){
        model.addAttribute("products", productService.findAllProducts());
        return "catalogue/products/list";
    }

    @GetMapping("/create")
    public String getNewProductPage(){
        return "catalogue/products/new_product";
    }


    @PostMapping("/create")
    public String createProduct(@Valid CreateProductRequest request, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("request", request);
            model.addAttribute("errors", bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList()))
            ;
            return "catalogue/products/new_product";
        }else{
            Product product = productService.createProduct(request);
            return String.format("redirect:/catalogue/products/%d", product.getId());
        }

    }




}