package com.teanlime.data.categorylisting.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FacetsModel {

    @SerializedName("FacetValues")
    private final List<FacetValuesModel> facetValues;

    @SerializedName("Sequence")
    private final Integer sequence;

    @SerializedName("Name")
    private final String name;

    @SerializedName("Id")
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
