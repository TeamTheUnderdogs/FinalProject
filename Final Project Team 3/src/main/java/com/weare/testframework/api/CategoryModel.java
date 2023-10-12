package com.weare.testframework.api;

public class CategoryModel {
    private int id;
    private String name;

    public CategoryModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
