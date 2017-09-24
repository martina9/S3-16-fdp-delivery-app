package com.fastdeliveryservice.domain;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author  mGabellini
 */

public class IngredientDto {

    public IngredientDto()
    {
        productRestourants=new ArrayList<>();
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setId(String id) {
        Id = id;
    }

    private String code ;

    private String Id;


    private CategoryDto category;

    private Collection<ProductRestaurantDto> productRestourants;


    public CategoryDto getCategory() {
        return category;
    }
    public void setCategory(CategoryDto category) {
        this.category = category;
    }

}

