package com.fastdeliveryservice.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author  mGabellini
 */

public class OrderDto implements Serializable {

    public List<ProductRestaurantDto> getProductRestaurants() {
        return productRestaurants;
    }

    public void setProductRestaurants(List<ProductRestaurantDto> productRestaurants) {
        this.productRestaurants = productRestaurants;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public OrderDto() {
        productRestaurants = new ArrayList<>();
    }

    private int Id;

    private int userId;

    private List<ProductRestaurantDto> productRestaurants;

    private double vatPrice ;

    private String deliveryType;

    private double netPrice;

    private Date ConfirmationDate ;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public double getVatPrice() {
        return vatPrice;
    }

    public void setVatPrice(double vatPrice) {
        this.vatPrice = vatPrice;
    }

    public double getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(double netPrice) {
        this.netPrice = netPrice;
    }

    public Date getConfirmationDate() {
        return ConfirmationDate;
    }

    public void setConfirmationDate(Date confirmationDate) {
        ConfirmationDate = confirmationDate;
    }

    public void setDeliveryType(String deliveryType) { this.deliveryType = deliveryType; }

    public String getDeliveryType() {return deliveryType;}
}

