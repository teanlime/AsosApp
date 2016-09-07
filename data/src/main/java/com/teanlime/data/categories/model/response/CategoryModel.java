package com.teanlime.data.categories.model.response;

import com.google.gson.annotations.SerializedName;

public class CategoryModel {

    @SerializedName("ProductCount")
    private final Integer productCount;

    @SerializedName("CategoryId")
    private final String categoryId;

    @SerializedName("Name")
    private final String name;

    public CategoryModel(String categoryId, String name, Integer productCount) {
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
