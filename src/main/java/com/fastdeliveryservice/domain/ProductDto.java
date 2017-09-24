package com.fastdeliveryservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author  mGabellini
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto implements Serializable {
    private int Id;

    private String name;

    @JsonIgnore
    private String ingredients;

    private String code ;

    private Double price;

    private CategoryDto category;

    /**
     * Returns an void from setId.
     *
     * @param  id
     */

    public void setId(int id) {
        Id = id;
    }

    /**
     * Returns an void from getId.
     *
     */

    public int getId() {
        return Id;
    }


    /**
     * Returns an void from setName.
     *
     * @param  name
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns an void from getName.
     *
     */

    public String getName() {
        return name;
    }

    /**
     * Returns an void from setPrice.
     *
     * @param  price
     */

    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Returns an void from getPrice.
     *
     */

    public Double getPrice() {
        return price;
    }

    /**
     * Returns an void from setIngredients.
     *
     * @param  ingredients
     */

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * Returns an string from getIngredients.
     *
     */
    @JsonIgnore
    public String getIngredients() {
        return ingredients;
    }

    /**
     * Returns an void from setCode.
     *
     * @param  code
     */

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Returns an string from getCode.
     *
     */

    public String getCode() {
        return code;
    }

    /**
     * Returns an void from setCategory.
     *
     * @param  category
     */

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    /**
     * Returns an CategoryDto from getCategory.
     *
     * @return CategoryDto
     * @see CategoryDto
     */

    public CategoryDto getCategory() {
        return category;
    }
}