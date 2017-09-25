package com.fastdeliveryservice.domain;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author  mGabellini
 */

public class IngredientDto {

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    private String code ;
    private String name;
    private String id;


}

