package com.teanlime.data.categorylisting.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FacetsModel {

    @SerializedName("FacetValues")
    @Expose
    private final List<FacetValuesModel> facetValues;
    @SerializedName("Sequence")
    @Expose
    private final Integer sequence;
    @SerializedName("Name")
    @Expose
    private final String name;
    @SerializedName("Id")
    @Expose
    private final String id;

    public FacetsModel(List<FacetValuesModel> facetValues, Integer sequence, String name, String id) {
        this.facetValues = facetValues;
        this.sequence = sequence;
        this.name = name;
        this.id = id;
    }

    public List<FacetValuesModel> getFacetValues() {
        return facetValues;
    }

    public Integer getSequence() {
        return sequence;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "FacetsModel{" +
                "facetValues=" + facetValues +
                ", sequence=" + sequence +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
