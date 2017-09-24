package com.fastdeliveryservice.viewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Martina Gabellini
 */

public class SellerViewModel {

    private String code;
    private int IdSeller;
    private String name;
    private String phoneNumber;
    private String email;
    private String city;
    private String street;
    private String zipCode;
 //   private List<Integer> productRestaurants;

    public SellerViewModel() {
 //       productRestaurants = new ArrayList<>();
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
//
//    public Collection<Integer> getProductRestaurants() {
//        return productRestaurants;
//    }
//
//    public void setProductRestaurants(List<Integer> productRestaurants) {
//        this.productRestaurants = productRestaurants;
//    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getIdSeller() {
        return IdSeller;
    }

    public void setIdSeller(int idSeller) {
        IdSeller = idSeller;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

