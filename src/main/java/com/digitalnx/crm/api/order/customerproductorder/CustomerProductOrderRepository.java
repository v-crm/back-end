package com.digitalnx.crm.api.order.customerproductorder;

import com.digitalnx.crm.api.user.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerProductOrderRepository extends JpaRepository<CustomerProductOrder, Integer> {
    List<CustomerProductOrder> findByUser(Optional<User> user);
}
