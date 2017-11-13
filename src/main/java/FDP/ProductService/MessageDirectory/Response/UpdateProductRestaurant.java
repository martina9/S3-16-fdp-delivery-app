package FDP.ProductService.MessageDirectory.Response;

import java.io.Serializable;

/**
 * Created by Martina on 01/10/2017.
 */

public class UpdateProductRestaurant implements Serializable {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
}
