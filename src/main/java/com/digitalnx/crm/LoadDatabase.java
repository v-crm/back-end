package com.digitalnx.crm;

import com.digitalnx.crm.api.order.ProductOrderRepository;
import com.digitalnx.crm.api.order.customerproductorder.CustomerProductOrder;
import com.digitalnx.crm.api.order.customerproductorder.CustomerProductOrderRepository;
import com.digitalnx.crm.api.order.ProductOrder;
import com.digitalnx.crm.api.product.Product;
import com.digitalnx.crm.api.product.ProductRepository;
import com.digitalnx.crm.api.product.productproperty.ProductProperty;
import com.digitalnx.crm.api.product.productproperty.ProductPropertyRepository;
import com.digitalnx.crm.api.user.UserService;
import com.digitalnx.crm.api.user.user.User;
import com.digitalnx.crm.api.user.user.UserRepository;
import com.digitalnx.crm.api.user.userrole.UserRole;
import com.digitalnx.crm.api.ui.UI;
import com.digitalnx.crm.api.ui.UIRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
class LoadDatabase {
    @Bean
    CommandLineRunner initDatabase(UserService userService,
                                   ProductOrderRepository orderRepository,
                                   UserRepository userRepository,
                                   ProductRepository productRepo,
                                   CustomerProductOrderRepository customerOrderRepository,
                                   ProductPropertyRepository productPropertyRepository,
                                   UIRepository uiRepository) {
        return args -> {
            UI ui = new UI( "صابون های ذستساز ایرات صابون", null, "black", "white", "FA", "ASD", "123", null);
            uiRepository.save(ui);

            productRepo.deleteAll();
            customerOrderRepository.deleteAll();
            productPropertyRepository.deleteAll();
            userRepository.deleteAll();

            userService.saveUserRole(new UserRole("ADMIN"));
            userService.saveUserRole(new UserRole("MANAGER"));
            userService.saveUserRole(new UserRole("USER"));
            User user = new User("Iman", "F.", "imanfa", "password", "0000000", "Ahvaz", "91000000", 0000000);
            userService.saveUser(user);
            userService.saveUser(new User("Mamad", "Rez", "mmd", "password", "0000000", "Ghale", "91000000", 0000000));

            userService.addRoleToUser("imanfa", "ADMIN");
            userService.addRoleToUser("imanfa", "USER");

            userService.addRoleToUser("mmd", "USER");

            ProductProperty property = new ProductProperty("Cleansing");
            ProductProperty property2 = new ProductProperty("Anti jush");

            Set<ProductProperty> properties = new HashSet<>();
            properties.add(property);
            properties.add(property2);

            Product product = new Product("Soap1", 100000,10, "Rial", 0, "asdasd", properties);
            Product product2 = new Product("Soap2", 200000, 10, "Rial", 0, "asdasd");

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
            CustomerProductOrder customerOrder1 = new CustomerProductOrder(user, orders);

            customerOrderRepository.save(customerOrder1);
        };
    }
}
