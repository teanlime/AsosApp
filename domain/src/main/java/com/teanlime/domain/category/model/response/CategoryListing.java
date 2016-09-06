package com.teanlime.domain.category.model.response;

import java.util.List;

public class CategoryListing {

    private final List<Listings> alsoSearched;
    private final List<Listings> listings;
    private final List<Facets> facets;

    private final String description;
    private final String redirectUrl;
    private final String sortType;

    private final Integer itemCount;

    public CategoryListing(List<Listings> alsoSearched,
                           List<Listings> listings,
                           List<Facets> facets,
                           String description,
                           String redirectUrl,
                           Integer itemCount,
                           String sortType) {

        this.alsoSearched = alsoSearched;
        this.description = description;
        this.redirectUrl = redirectUrl;
        this.itemCount = itemCount;
        this.listings = listings;
        this.sortType = sortType;
        this.facets = facets;
    }

    public List<Listings> getAlsoSearched() {
        return alsoSearched;
    }

    public List<Listings> getListings() {
        return listings;
    }

    public List<Facets> getFacets() {
        return facets;
    }

    public String getDescription() {
        return description;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public String getSortType() {
        return sortType;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    @Override
    public String toString() {
        return "CategoryListing{" +
                "alsoSearched=" + alsoSearched +
                ", listings=" + listings +
                ", facets=" + facets +
                ", description='" + description + '\'' +
                ", redirectUrl='" + redirectUrl + '\'' +
                ", sortType='" + sortType + '\'' +
                ", itemCount=" + itemCount +
                '}';
    }
}
