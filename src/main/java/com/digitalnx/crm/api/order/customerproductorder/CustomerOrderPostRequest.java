package com.digitalnx.crm.api.order.customerproductorder;

import java.util.Map;
import java.util.Set;

public class CustomerOrderPostRequest {
    private int userId;
    private Map<String, Integer> productIdToQuantityMap;

    public CustomerOrderPostRequest(int userId, Map<String, Integer> productIdToQuantityMap) {
        this.userId = userId;
        this.productIdToQuantityMap = productIdToQuantityMap;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Map<String, Integer> getProductIdToQuantityMap() {
        return productIdToQuantityMap;
    }

    public void setProductIdToQuantityMap(Map<String, Integer> productIdToQuantityMap) {
        this.productIdToQuantityMap = productIdToQuantityMap;
    }
}
