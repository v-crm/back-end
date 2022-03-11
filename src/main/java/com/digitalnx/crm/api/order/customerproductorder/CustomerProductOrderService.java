package com.digitalnx.crm.api.order.customerproductorder;

import com.digitalnx.crm.api.order.ProductOrder;
import com.digitalnx.crm.api.order.ProductOrderRepository;
import com.digitalnx.crm.api.product.Product;
import com.digitalnx.crm.api.product.ProductRepository;
import com.digitalnx.crm.api.user.user.User;
import com.digitalnx.crm.api.user.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerProductOrderService {

    @Autowired
    CustomerProductOrderRepository customerProductOrderRepository;
    @Autowired
    ProductOrderRepository productOrderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    public List<CustomerProductOrder> getOrders() {
        return customerProductOrderRepository.findAll();
    }

    public List<CustomerProductOrder> getOrdersFromAUser(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return customerProductOrderRepository.findByUser(user);
    }

    public void newOrder (CustomerOrderPostRequest customerOrderPostRequest) {
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

                    var customerProductOrder = new CustomerProductOrder();
                    customerProductOrder.setDate(new Date());
                    customerProductOrder.setOrders(productOrders);
                    customerProductOrder.setUser(user);

                    customerProductOrderRepository.save(customerProductOrder);
                },
                () -> {
                    throw new RuntimeException("User not found!");
                }
        );
    }
}
