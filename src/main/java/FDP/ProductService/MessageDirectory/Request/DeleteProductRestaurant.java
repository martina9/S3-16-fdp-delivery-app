package FDP.ProductService.MessageDirectory.Request;

import java.io.Serializable;

/**
 * Created by Martina on 01/10/2017.
 */

public class DeleteProductRestaurant implements Serializable {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
