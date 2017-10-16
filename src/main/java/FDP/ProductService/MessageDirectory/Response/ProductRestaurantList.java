package FDP.ProductService.MessageDirectory.Response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martina on 01/10/2017.
 */

public class ProductRestaurantList implements Serializable{


    public ProductRestaurantList()
    {
        items = new ArrayList<>();
    }

    private List<ProductRestaurantInfo> items;

    public List<ProductRestaurantInfo> getItems() {
        return items;
    }

    public void setItems(List<ProductRestaurantInfo> items) {
        this.items = items;
    }
}
