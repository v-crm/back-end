package com.digitalnx.crm.api.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path="/products")
    public @ResponseBody
    Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @PostMapping(path="/product")
    public @ResponseBody String addProduct (@RequestParam String name, @RequestParam int price) {
        return "Saved";
    }
}
