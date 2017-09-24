package com.fastdeliveryservice.viewModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Martina Gabellini
 */

public class RestaurantViewModel {
    private int Id;

    private String code;
    private String name;
    private String city;
    private String street;
    private String zipCode;

    private List<Integer> productRestaurants;

    public RestaurantViewModel() {
        productRestaurants = new ArrayList<>();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Collection<Integer> getProductRestaurants() {
        return productRestaurants;
    }

    public void setProductRestaurants(List<Integer> productRestaurants) {
        this.productRestaurants = productRestaurants;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}