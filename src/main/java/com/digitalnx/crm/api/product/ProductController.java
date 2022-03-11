package com.digitalnx.crm.api.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping(path="/products")
    public List<Product> getAllProducts() {
        return productService.getProducts();
    }

    @DeleteMapping("/product/{id}")
    void updateProduct(@PathVariable Integer id){
        productService.deleteProduct(id);
    }
}
