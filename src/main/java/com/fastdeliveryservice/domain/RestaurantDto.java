package com.fastdeliveryservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author  mGabellini
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestaurantDto implements Serializable {

    public String email;
    public String phoneNumber;
    private Collection<AddressRestaurantDto> addressRestaurants;
    private Collection<ProductRestaurantDto> productRestourants;
    private String code;
    private int Id;
    private String name;

    public RestaurantDto() {
        productRestourants = new ArrayList<>();
        addressRestaurants = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Collection<AddressRestaurantDto> getAddressRestaurants() {
        return addressRestaurants;
    }

    public void setAddressRestaurants(Collection<AddressRestaurantDto> addressRestaurants) {
        this.addressRestaurants = addressRestaurants;
    }

    public Collection<ProductRestaurantDto> getProductRestourants() {
        return productRestourants;
    }

    public void setProductRestourants(Collection<ProductRestaurantDto> productRestourants) {
        this.productRestourants = productRestourants;
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

