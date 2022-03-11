package com.digitalnx.crm.api.order.customerproductorder;

import com.digitalnx.crm.api.order.ProductOrder;
import com.digitalnx.crm.api.order.ProductOrderRepository;
import com.digitalnx.crm.api.product.Product;
import com.digitalnx.crm.api.product.ProductRepository;
import com.digitalnx.crm.api.user.user.User;
import com.digitalnx.crm.api.user.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CustomerProductOrderController {
    @Autowired
    CustomerProductOrderService customerProductOrderService;

    @GetMapping("/orders")
    List<CustomerProductOrder> allOrders() {
        return customerProductOrderService.getOrders();
    }

    @GetMapping("/order/user/{id}")
    Iterable<CustomerProductOrder> allOrdersFromUser(@PathVariable Integer id) {
        return customerProductOrderService.getOrdersFromAUser(id);
    }

    @PostMapping("/order")
    ResponseEntity<Object> postOrder(@RequestBody CustomerOrderPostRequest customerOrderPostRequest) {
        try {
            customerProductOrderService.newOrder(customerOrderPostRequest);
         } catch(Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), new HttpHeaders(), HttpStatus.FORBIDDEN);
        }
        return ResponseEntity.ok().build();
    }
}
