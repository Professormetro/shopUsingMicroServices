package org.chernov.managerapp.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.chernov.managerapp.dto.UpdateProductRequest;
import org.chernov.managerapp.entity.Product;
import org.chernov.managerapp.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
@RequestMapping("catalogue/products/{productId:\\d+}")
public class ProductController {

    private final ProductService productService;

    @ModelAttribute("product")
    public Optional<Product> product(@PathVariable("productId") int productId){
        return productService.findProduct(productId);
    }


    @GetMapping
    public String getProductPage(@ModelAttribute("product") Product product, Model model){
        model.addAttribute("product", product);
        return "catalogue/products/product";
    }


    @GetMapping("edit")
    public String getProductEditPage(@ModelAttribute("product") Product product, Model model){
        model.addAttribute("product", product);
        return "catalogue/products/edit";
    }

    @PostMapping("edit")
    public String saveProduct(@ModelAttribute("product") Product product, UpdateProductRequest updateProductRequest){
        productService.updateProduct(product.getId(), updateProductRequest);
        return String.format("redirect:/catalogue/products/%d", product.getId());
    }

    @PostMapping("delete")
    public String deleteProduct(@ModelAttribute("product") Product product){
        productService.deleteProduct(product);
        return "redirect:/catalogue/products/list";
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(NoSuchElementException exception, Model model, HttpServletResponse response){
        response.setStatus(HttpStatus.NOT_FOUND.value());
        model.addAttribute("error", exception.getMessage());
        return "errors/404";
    }

}
