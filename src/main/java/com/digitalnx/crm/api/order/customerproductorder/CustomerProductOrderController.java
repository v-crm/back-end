package com.digitalnx.crm.api.order.customerproductorder;

import com.digitalnx.crm.api.order.ProductOrder;
import com.digitalnx.crm.api.order.ProductOrderRepository;
import com.digitalnx.crm.api.product.Product;
import com.digitalnx.crm.api.product.ProductRepository;
import com.digitalnx.crm.api.user.user.User;
import com.digitalnx.crm.api.user.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CustomerProductOrderController {
    @Autowired
    CustomerProductOrderRepository customerOrderRepository;
    @Autowired
    ProductOrderRepository productOrderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/orders")
    Iterable<CustomerProductOrder> allOrders() {
        return customerOrderRepository.findAll();
    }

    @GetMapping("/order/user/{id}")
    Iterable<CustomerProductOrder> allOrdersFromUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        return customerOrderRepository.findByUser(user);
    }

    @PostMapping("/order")
    ResponseEntity<Object> postOrder(@RequestBody CustomerOrderPostRequest customerOrderPostRequest) {
        // TODO Exception handling
        Optional<User> optUser = userRepository.findById(customerOrderPostRequest.getUserId());
        if (optUser.isEmpty()) throw new RuntimeException("User not found!");
        optUser.ifPresentOrElse(
                user -> {
                    Set<ProductOrder> productOrders = new HashSet<>();
                    customerOrderPostRequest.getProductIdToQuantityMap().forEach(
                            (productId, orderQuantity) -> {
                                Optional<Product> optProduct = productRepository.findById(Integer.parseInt(productId));
                                optProduct.ifPresentOrElse(
                                        product -> {
                                            if (orderQuantity < product.getUnitsInStock()) {
                                                product.setUnitsInStock(product.getUnitsInStock() - orderQuantity);
                                                productRepository.save(product);
                                            } else {
                                                throw new RuntimeException("Not enough quantities in stock!");
                                            }
                                            ProductOrder productOrder = new ProductOrder(product, orderQuantity, null);
                                            productOrderRepository.save(productOrder);
                                            productOrders.add(productOrder);
                                        },
                                        () -> {
                                            throw new RuntimeException("Product does not exists!");
                                        });
                            });
                    CustomerProductOrder customerProductOrder = new CustomerProductOrder(user, productOrders);
                    customerOrderRepository.save(customerProductOrder);
                },
                () -> {
                    throw new RuntimeException("User not found!");
                }
        );
        return ResponseEntity.noContent().build();
    }
}
