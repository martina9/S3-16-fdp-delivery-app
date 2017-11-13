package FDP.ProductService.MessageDirectory.Request;

import java.io.Serializable;

/**
 * Created by Martina on 01/10/2017.
 */

public class CategoryList implements Serializable{

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String code;

}
