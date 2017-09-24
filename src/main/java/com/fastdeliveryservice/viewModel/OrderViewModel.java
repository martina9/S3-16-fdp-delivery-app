package com.fastdeliveryservice.viewModel;

import java.util.List;

/**
 * Created by Martina
 */

public class OrderViewModel {
    private List<Integer> products;
    private String deliveryType;
    private int userId;

    public int getUserId() { return userId; }

    public void setUserId(int userId) { this.userId = userId; }

    public List<Integer> getProducts() {
        return products;
    }

    public void setProducts(List<Integer> products) {
        this.products = products;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }
}
