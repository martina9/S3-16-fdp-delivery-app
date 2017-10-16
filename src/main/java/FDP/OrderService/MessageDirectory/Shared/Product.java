package FDP.OrderService.MessageDirectory.Shared;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Martina on 04/10/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product
{
    public Product() {
    }

    public Product(int quantity, int productId) {
        this.quantity = quantity;
        this.productId = productId;

    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    private int quantity;
    private int productId;
}
