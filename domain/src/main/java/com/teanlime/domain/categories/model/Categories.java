package com.teanlime.domain.categories.model;

import java.util.List;

public class Categories {

    private final List<Category> listing;
    private final String description;
    private final String sortType;

    public Categories(String description, List<Category> listing, String sortType) {
        this.description = description;
        this.listing = listing;
        this.sortType = sortType;
    }

    public List<Category> getListing() {
        return listing;
    }

    public String getDescription() {
        return description;
    }

    public String getSortType() {
        return sortType;
    }
}