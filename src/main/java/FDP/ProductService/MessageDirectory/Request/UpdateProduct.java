package FDP.ProductService.MessageDirectory.Request;

import java.io.Serializable;

/**
 * Created by Martina on 01/10/2017.
 */
public class UpdateProduct  implements Serializable {

    private int id;
    private String name;
    private String code;
    private int categoryId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

}
