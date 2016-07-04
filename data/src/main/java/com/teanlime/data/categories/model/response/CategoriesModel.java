package com.teanlime.data.categories.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoriesModel {

    @SerializedName("Listing")
    @Expose
    private final List<CategoryModel> listing;

    @SerializedName("Description")
    @Expose
    private final String description;

    @SerializedName("SortType")
    @Expose
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