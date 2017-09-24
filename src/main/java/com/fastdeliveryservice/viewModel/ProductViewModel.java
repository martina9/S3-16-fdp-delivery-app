package com.fastdeliveryservice.viewModel;


import java.util.List;

/**
 * Created by Martina Gabellini
 */

public class ProductViewModel {
    private int id;

    private String name;
    private String ingredients;

    private double price;

    private List<OrderViewModel> orders;
    private SellerViewModel seller;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public double getPrice() {
        return price;
    }

    public SellerViewModel getSeller() {
        return seller;
    }

    public List<OrderViewModel> getOrders() {
        return orders;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSeller(SellerViewModel seller) {
        this.seller = seller;
    }

    public void setOrders(List<OrderViewModel> orders) {
        this.orders = orders;
    }
}
