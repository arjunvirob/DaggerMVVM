package com.myapps.daggermvvm.model;

public class Category {
    private String name;
    private String image;
    private String categoryId;

    public Category(String name, String image, String categoryId) {
        this.name = name;
        this.image = image;
        this.categoryId = categoryId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
