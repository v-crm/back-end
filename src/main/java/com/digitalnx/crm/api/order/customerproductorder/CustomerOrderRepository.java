package com.digitalnx.crm.api.order.customerproductorder;

import org.springframework.data.repository.CrudRepository;

public interface CustomerOrderRepository extends CrudRepository<CustomerProductOrder, Integer> {
}
