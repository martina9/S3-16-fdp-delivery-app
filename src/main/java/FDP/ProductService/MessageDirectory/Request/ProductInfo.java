package FDP.ProductService.MessageDirectory.Request;

import java.io.Serializable;

/**
 * Created by Martina on 01/10/2017.
 */

public class ProductInfo implements Serializable{

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int id ;

    @Override
    public String toString() {
        return "ProductInfo{" +
                "id=" + id  +'}';
    }
}
