package com.fastdeliveryservice.viewModel;


/**
 * Created by Martina Gabellini
 */

public class CategoryViewModel {
    private int id;
    private String name;

    public CategoryViewModel(){}

    public CategoryViewModel(int id, String name) {
        this.id = id;
        this.name = name;
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
