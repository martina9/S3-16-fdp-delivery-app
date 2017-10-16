package FDP.ProductService.MessageDirectory.Request;


import java.io.Serializable;

/**
 * Created by Martina on 03/10/2017.
 */
public class ProductRestaurantInfo implements Serializable{

    private int id ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
