package FDP.ProductService.MessageDirectory.Request;

import java.io.Serializable;

/**
 * Created by Martina on 01/10/2017.
 */

public class AddProductRestaurant implements Serializable {

    public AddProductRestaurant() {
    }

    public AddProductRestaurant(String name, int restaurantId, double price, String code, int productId) {
        this.name = name;
        this.restaurantId = restaurantId;
        this.price = price;
        this.productId = productId;
    }

    private String name;
    private int restaurantId;
    private double price;

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    private String code;

    private int productId;
}
