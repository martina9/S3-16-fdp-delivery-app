package FDP.OrderService.MessageDirectory.Request;

import FDP.OrderService.MessageDirectory.Shared.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Martina on 04/10/2017.
 */
public class ConfirmOrder
{
    public String deliveryType;
    public String address;
    public String city ;
    public String phoneNumber;
    public String email;
    public List<Product> products;
    private int userId;
    private double amount;
    private int restaurantId;

    public ConfirmOrder()
    {
        products = new ArrayList<Product>();
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public ConfirmOrder(int userId, double amount, String deliveryType, String address, String city, String phoneNumber, String email,int restaurantId) {
        this.userId = userId;
        this.amount = amount;
        this.deliveryType = deliveryType;
        this.address = address;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.restaurantId = restaurantId;
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

}
