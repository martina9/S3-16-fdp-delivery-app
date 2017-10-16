package com.fastdeliveryservice.viewModel;

/**
 * Created by Martina Gabellini
 */

public class RestaurantViewModel {
    private int Id;
    private String code;
    private String name;
    private String city;

    public RestaurantViewModel(int id, String code, String name, String city, String street, String zipCode, String phoneNumber) {
        Id = id;
        this.code = code;
        this.name = name;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
    }

    private String street;
    private String zipCode;
    private String phoneNumber;

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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