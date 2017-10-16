package com.fastdeliveryservice.domain;

import java.util.List;

/**
 * @author  mGabellini
 */

public class OrderView {

    private List<Integer> products;
    private String deliveryType;

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
