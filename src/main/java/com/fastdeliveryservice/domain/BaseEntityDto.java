package com.fastdeliveryservice.domain;

/**
 * Created by Martina on 24/09/2017.
 */

public class BaseEntityDto {

    private int Id;
    private String name;
    private String code;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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
}

