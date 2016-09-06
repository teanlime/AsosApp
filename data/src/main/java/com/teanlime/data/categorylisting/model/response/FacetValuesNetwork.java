package com.teanlime.data.categorylisting.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FacetValuesNetwork {

    @SerializedName("Count")
    @Expose
    private final Integer count;
    @SerializedName("Name")
    @Expose
    private final String name;
    @SerializedName("Id")
    @Expose
    private final String id;

    public FacetValuesNetwork(Integer count, String name, String id) {
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