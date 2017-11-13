package FDP.ProductService.MessageDirectory.Response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martina on 01/10/2017.
 */

public class ProductList implements Serializable{


    public ProductList()
    {
        items = new ArrayList<>();
    }
    private List<ProductInfo> items;

    public List<ProductInfo> getItems() {
        return items;
    }

    public void setItems(List<ProductInfo> items) {
        this.items = items;
    }
}
