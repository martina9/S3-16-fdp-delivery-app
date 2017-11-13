package com.fastdeliveryservice.viewModel;

import FDP.OrderService.MessageDirectory.Shared.Product;

import java.util.Date;
import java.util.List;

/**
 * Created by Martina
 */

public class OrderViewModel {
    private String deliveryType;
    private String address;
    private String city ;
    private String phoneNumber;
    private String email;
    private List<Product> products;
    private int userId;
    private int restaurantId;
    private double amount;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderViewModel(int id , String deliveryType, String address, String city, String phoneNumber, String email, int userId, double amount, Date confirmationDate) {
        this.deliveryType = deliveryType;
        this.address = address;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userId = userId;
        this.amount = amount;
        this.id = id;
    }

    public OrderViewModel(String deliveryType, String address, String city, String phoneNumber, String email, int userId, double amount, Date confirmationDate) {
        this.deliveryType = deliveryType;
        this.address = address;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userId = userId;
        this.amount = amount;
        this.id = id;
    }

    public OrderViewModel() {

    }

    public String getDeliveryType() {
        return deliveryType;

    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
}
