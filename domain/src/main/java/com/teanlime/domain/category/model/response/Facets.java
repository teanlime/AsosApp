package com.teanlime.domain.category.model.response;

import java.util.List;

public class Facets {

    private final List<FacetValues> facetValues;
    private final Integer sequence;
    private final String name;
    private final String id;

    public Facets(List<FacetValues> facetValues, Integer sequence, String name, String id) {
        this.facetValues = facetValues;
        this.sequence = sequence;
        this.name = name;
        this.id = id;
    }

    public List<FacetValues> getFacetValues() {
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
        return "Facets{" +
                "facetValues=" + facetValues +
                ", sequence=" + sequence +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
