package com.teanlime.domain.categories.model.response;

public class Category {

    private final Integer productCount;
    private final String categoryId;
    private final String name;

    public Category(String categoryId, String name, Integer productCount) {
        this.productCount = productCount;
        this.categoryId = categoryId;
        this.name = name;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }
}
