package com.digitalnx.crm.api.order.customerproductorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class CustomerOrderController {
    @Autowired
    CustomerOrderRepository customerOrderRepository;

    @GetMapping("/all")
    Iterable<CustomerProductOrder> allOrders() {
        return customerOrderRepository.findAll();
    }
}
