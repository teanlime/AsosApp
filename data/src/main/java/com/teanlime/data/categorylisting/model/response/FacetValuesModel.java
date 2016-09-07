package com.teanlime.data.categorylisting.model.response;

import com.google.gson.annotations.SerializedName;

public class FacetValuesModel {

    @SerializedName("Count")
    private final Integer count;

    @SerializedName("Name")
    private final String name;

    @SerializedName("Id")
    private final String id;

    public FacetValuesModel(Integer count, String name, String id) {
        this.count = count;
        this.name = name;
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }


}