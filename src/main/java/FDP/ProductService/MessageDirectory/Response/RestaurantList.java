package FDP.ProductService.MessageDirectory.Response;

import FDP.ProductService.MessageDirectory.Request.RestaurantInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martina on 01/10/2017.
 */

public class RestaurantList implements Serializable{

    public RestaurantList()
    {
        items = new ArrayList<>();
    }
    private List<RestaurantInfo> items;

    public List<RestaurantInfo> getItems() {
        return items;
    }

    public void setItems(List<RestaurantInfo> items) {
        this.items = items;
    }
}
