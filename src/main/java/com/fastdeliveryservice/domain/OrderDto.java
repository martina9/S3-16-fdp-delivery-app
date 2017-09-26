package com.fastdeliveryservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author  mGabellini
 */
@JsonIgnoreProperties(ignoreUnknown = true)
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String deliveryType;

    private String city;

    private String phoneNumber;

    public String email;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private double amount;

    private Date ConfirmationDate ;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

