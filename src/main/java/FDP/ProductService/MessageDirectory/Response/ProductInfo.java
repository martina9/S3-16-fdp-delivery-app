package FDP.ProductService.MessageDirectory.Response;

import java.io.Serializable;

/**
 * Created by Martina on 01/10/2017.
 */
public class ProductInfo implements Serializable{

    private int id;

    private String name;
    private String code ;
    private int categoryId;
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * Returns an void from getId.
     *
     */

    public int getId() {
        return id;
    }

    /**
     * Returns an void from setId.
     *
     * @param  id

     */

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns an void from getName.
     *
     */

    public String getName() {
        return name;
    }

    /**
     * Returns an void from setName.
     *
     * @param  name
     */

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**

     * Returns an string from getCity.
     *
     */

    public String getCode() {
        return code;
    }

    /**
     * Returns an void from setCity.
     *
     * @param  code
     */

    public void setCode(String code) {
        this.code = code;
    }


}
