package com.digitalnx.crm;

import com.digitalnx.crm.api.order.CustomerOrder;
import com.digitalnx.crm.api.order.CustomerOrderRepository;
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

import java.util.HashSet;
import java.util.Set;

@Configuration
class LoadDatabase {
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, ProductRepository productRepo, CustomerOrderRepository customerOrderRepository, ProductPropertyRepository productPropertyRepository) {
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

            Product p1 = new Product("Soap1", 100000,"Rial", 0, "asdasd", properties);
            Product p2 = new Product("Soap2", 200000, "Rial", 0, "asdasd");

            productPropertyRepository.save(property);
            productPropertyRepository.save(property2);
            productRepo.save(p1);
            productRepo.save(p2);

            CustomerOrder o1 = new CustomerOrder();
            Set<Product> products = new HashSet<>();
            products.add(p1);
            products.add(p2);
            o1.setProduct(products);
            customerOrderRepository.save(o1);
        };
    }
}
