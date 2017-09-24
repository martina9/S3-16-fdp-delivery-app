package com.fastdeliveryservice.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Martina Gabellini
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductRestaurantDto implements Serializable  {

    public ProductRestaurantDto(){}

    public ProductRestaurantDto(int id)
    {
        this.id = id;
    }

    private int id ;
    @JsonProperty("price")
    private double price;
    private int quantity;
    private ProductDto product;
    private RestaurantDto restaurant;
    private String name ;

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) { this.product = product; }

    public RestaurantDto getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantDto restaurant) {
        this.restaurant = restaurant;
    }



}

