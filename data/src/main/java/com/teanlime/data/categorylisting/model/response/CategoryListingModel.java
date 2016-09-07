package com.teanlime.data.categorylisting.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryListingModel {

    @SerializedName("AlsoSearched")
    private final List<ListingsModel> alsoSearched;

    @SerializedName("Listings")
    private final List<ListingsModel> listings;

    @SerializedName("Facets")
    private final List<FacetsModel> facets;

    @SerializedName("Description")
    private final String description;

    @SerializedName("RedirectUrl")
    private final String redirectUrl;

    @SerializedName("SortType")
    private final String sortType;

    @SerializedName("ItemCount")
    private final Integer itemCount;

    public CategoryListingModel(List<ListingsModel> alsoSearched,
                                List<ListingsModel> listings,
                                List<FacetsModel> facets,
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

    public List<ListingsModel> getAlsoSearched() {
        return alsoSearched;
    }

    public List<ListingsModel> getListings() {
        return listings;
    }

    public List<FacetsModel> getFacets() {
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
        return "CategoryListingModel{" +
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
