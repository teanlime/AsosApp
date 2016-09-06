package com.teanlime.domain.categorylisting.model.response;

public class FacetValues {

    private final Integer count;
    private final String name;
    private final String id;

    public FacetValues(Integer count, String name, String id) {
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