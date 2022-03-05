package com.digitalnx.crm;

import com.digitalnx.crm.api.order.ProductOrderRepository;
import com.digitalnx.crm.api.order.customerproductorder.CustomerProductOrder;
import com.digitalnx.crm.api.order.customerproductorder.CustomerOrderRepository;
import com.digitalnx.crm.api.order.ProductOrder;
import com.digitalnx.crm.api.product.Product;
import com.digitalnx.crm.api.product.ProductRepository;
import com.digitalnx.crm.api.product.productproperty.ProductProperty;
import com.digitalnx.crm.api.product.productproperty.ProductPropertyRepository;
import com.digitalnx.crm.api.user.User;
import com.digitalnx.crm.api.user.UserRepository;
import com.digitalnx.crm.api.user.UserRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
class LoadDatabase {
    @Bean
    CommandLineRunner initDatabase(ProductOrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepo, CustomerOrderRepository customerOrderRepository, ProductPropertyRepository productPropertyRepository) {
        return args -> {
            productRepo.deleteAll();
            customerOrderRepository.deleteAll();
            productPropertyRepository.deleteAll();
            userRepository.deleteAll();

            User user = new User("Iman", "F.", "password", UserRole.ADMIN, "0000000", "Ahvaz", "91000000", 0000000);
            userRepository.save(user);

            ProductProperty property = new ProductProperty("Cleansing");
            ProductProperty property2 = new ProductProperty("Anti jush");

            Set<ProductProperty> properties = new HashSet<>();
            properties.add(property);
            properties.add(property2);

            Product product = new Product("Soap1", 100000,"Rial", 0, "asdasd", properties);
            Product product2 = new Product("Soap2", 200000, "Rial", 0, "asdasd");

           productPropertyRepository.save(property);
           productPropertyRepository.save(property2);
           productRepo.save(product);
           productRepo.save(product2);

            ProductOrder order1 = new ProductOrder(product, 5,"");
            ProductOrder order2 = new ProductOrder(product2, 2,"");
            orderRepository.save(order1);
            orderRepository.save(order2);

            Set<ProductOrder> orders = new HashSet<>();
            orders.add(order1);
            orders.add(order2);
            CustomerProductOrder customerOrder1 = new CustomerProductOrder(user, orders, new Date());

            customerOrderRepository.save(customerOrder1);
        };
    }
}
