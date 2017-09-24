package com.fastdeliveryservice.domain;

import java.io.Serializable;

/**
 * @author  mGabellini
 */

public class OrderProductDto extends BaseEntityDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private double quantity;

    public double getQuantity() {
        return quantity;
    }

    public ProductRestaurantDto getProductRestourant() {
        return productRestourant;
    }

    public void setProductRestourant(ProductRestaurantDto productRestourant) {
        this.productRestourant = productRestourant;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    private ProductRestaurantDto productRestourant;
}

