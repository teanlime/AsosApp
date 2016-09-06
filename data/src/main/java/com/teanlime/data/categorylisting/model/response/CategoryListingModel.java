package com.teanlime.data.categorylisting.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryListingModel {

    @SerializedName("AlsoSearched")
    @Expose
    private final List<ListingsModel> alsoSearched;
    @SerializedName("Listings")
    @Expose
    private final List<ListingsModel> listings;
    @SerializedName("Facets")
    @Expose
    private final List<FacetsModel> facets;

    @SerializedName("Description")
    @Expose
    private final String description;
    @SerializedName("RedirectUrl")
    @Expose
    private final String redirectUrl;
    @SerializedName("SortType")
    @Expose
    private final String sortType;

    @SerializedName("ItemCount")
    @Expose
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
