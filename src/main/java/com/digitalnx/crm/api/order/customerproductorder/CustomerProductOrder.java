package com.digitalnx.crm.api.order.customerproductorder;

import com.digitalnx.crm.api.order.ProductOrder;
import com.digitalnx.crm.api.order.ProductOrderRepository;
import com.digitalnx.crm.api.product.Product;
import com.digitalnx.crm.api.product.ProductRepository;
import com.digitalnx.crm.api.user.user.User;
import com.digitalnx.crm.api.user.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
public class CustomerProductOrder {
    public CustomerProductOrder() {}

    public CustomerProductOrder(CustomerOrderPostRequest customerOrderPostRequest,
                                ProductOrderRepository productOrderRepository,
                                UserRepository userRepository,
                                ProductRepository productRepository
                                ) {
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

                    this.date = new Date();
                    this.orders = productOrders;
                    this.user = user;
                },
                () -> {
                    throw new RuntimeException("User not found!");
                }
        );

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @Temporal(value= TemporalType.TIMESTAMP)
    private Date date;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    Set<ProductOrder> orders;

    @OneToOne
    private User user;

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getId() {
        return Id;
    }

    public Set<ProductOrder> getOrders() {
        return orders;
    }

    public void setOrders(Set<ProductOrder> orders) {
        this.orders = orders;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
