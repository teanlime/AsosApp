package com.teanlime.data.categories.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoriesModel {

    @SerializedName("Listing")
    private final List<CategoryModel> listing;

    @SerializedName("Description")
    private final String description;

    @SerializedName("SortType")
    private final String sortType;

    public CategoriesModel(String description, List<CategoryModel> listing, String sortType) {
        this.description = description;
        this.listing = listing;
        this.sortType = sortType;
    }

    public List<CategoryModel> getListing() {
        return listing;
    }

    public String getDescription() {
        return description;
    }

    public String getSortType() {
        return sortType;
    }
}