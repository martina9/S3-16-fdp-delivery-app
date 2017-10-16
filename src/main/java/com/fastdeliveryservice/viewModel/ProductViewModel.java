package com.fastdeliveryservice.viewModel;


import java.util.List;

/**
 * Created by Martina Gabellini
 */

public class ProductViewModel {
    private int id;
    private String name;
    private String code;
    private int categoryId;
    private String categoryName;

    public ProductViewModel(){}

    public ProductViewModel(int id, String name, String code, int categoryId,String categoryName) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public String getCode() {
        return code;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
