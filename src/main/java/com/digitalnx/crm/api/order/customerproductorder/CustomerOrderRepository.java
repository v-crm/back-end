package com.digitalnx.crm.api.order.customerproductorder;

import com.digitalnx.crm.api.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerOrderRepository extends CrudRepository<CustomerProductOrder, Integer> {
    List<CustomerProductOrder> findByUser(Optional<User> user);
}
