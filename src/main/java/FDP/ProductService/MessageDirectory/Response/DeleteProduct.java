package FDP.ProductService.MessageDirectory.Response;

import java.io.Serializable;

/**
 * Created by Martina on 01/10/2017.
 */

public class DeleteProduct implements Serializable {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
