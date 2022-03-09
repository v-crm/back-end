package com.digitalnx.crm.api.order.customerproductorder;

import com.digitalnx.crm.api.user.user.User;
import com.digitalnx.crm.api.user.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/order")
public class CustomerOrderController {
    @Autowired
    CustomerOrderRepository customerOrderRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
    Iterable<CustomerProductOrder> allOrders() {
        return customerOrderRepository.findAll();
    }

    @GetMapping("/user/{id}")
    Iterable<CustomerProductOrder> allOrdersFromUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        return customerOrderRepository.findByUser(user);
    }
}
