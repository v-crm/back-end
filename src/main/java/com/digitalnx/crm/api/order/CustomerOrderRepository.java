package com.digitalnx.crm.api.order;

import org.springframework.data.repository.CrudRepository;

public interface CustomerOrderRepository extends CrudRepository<CustomerOrder, Integer> {
}
