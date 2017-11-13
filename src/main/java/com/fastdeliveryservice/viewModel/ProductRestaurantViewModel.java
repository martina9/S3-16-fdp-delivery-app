package com.fastdeliveryservice.viewModel;

import java.util.List;

/**
 * Created by Martina Gabellini
 */

public class ProductRestaurantViewModel {

    private int id;

    public ProductRestaurantViewModel() {
    }

    public ProductRestaurantViewModel(int id, double price, String name, int productId, int restaurantId, String productName, String restaurantName, int quantity) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.productId = productId;
        this.restaurantId = restaurantId;
        this.productName = productName;
        this.restaurantName = restaurantName;
        this.quantity = quantity;
    }

    private double price;
    private String name;
    private int productId;
    private int restaurantId;
    private String productName;
    private String restaurantName;
    private int quantity;
    private List<String> ingredients;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}

