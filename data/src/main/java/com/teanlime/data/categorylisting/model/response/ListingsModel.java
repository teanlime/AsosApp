package com.teanlime.data.categorylisting.model.response;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.List;

public class ListingsModel {

    @SerializedName("ProductImageUrl")
    private final List<String> productImageUrl;

    @SerializedName("BasePrice")
    private final BigDecimal basePrice;

    @SerializedName("HasMoreColours")
    private final Boolean hasMoreColours;

    @SerializedName("isInSet")
    private final Boolean inSet;

    @SerializedName("PreviousPrice")
    private final String previousPrice;

    @SerializedName("CurrentPrice")
    private final String currentPrice;

    @SerializedName("Title")
    private final String title;

    @SerializedName("Brand")
    private final String brand;

    @SerializedName("RRP")
    private final String rrp;

    @SerializedName("ProductId")
    private final Long productId;

    public ListingsModel(List<String> productImageUrl,
                         Boolean hasMoreColours,
                         BigDecimal basePrice,
                         String previousPrice,
                         String currentPrice,
                         Long productId,
                         Boolean inSet,
                         String title,
                         String brand,
                         String rrp) {

        this.productImageUrl = productImageUrl;
        this.hasMoreColours = hasMoreColours;
        this.previousPrice = previousPrice;
        this.currentPrice = currentPrice;
        this.basePrice = basePrice;
        this.productId = productId;
        this.title = title;
        this.brand = brand;
        this.inSet = inSet;
        this.rrp = rrp;
    }

    public List<String> getProductImageUrl() {
        return productImageUrl;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public Boolean getHasMoreColours() {
        return hasMoreColours;
    }

    public Boolean getInSet() {
        return inSet;
    }

    public String getPreviousPrice() {
        return previousPrice;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public String getTitle() {
        return title;
    }

    public String getBrand() {
        return brand;
    }

    public String getRrp() {
        return rrp;
    }

    public Long getProductId() {
        return productId;
    }

    @Override
    public String toString() {
        return "ListingsModel{" +
                "productImageUrl=" + productImageUrl +
                ", basePrice=" + basePrice +
                ", hasMoreColours=" + hasMoreColours +
                ", inSet=" + inSet +
                ", previousPrice='" + previousPrice + '\'' +
                ", currentPrice='" + currentPrice + '\'' +
                ", title='" + title + '\'' +
                ", brand='" + brand + '\'' +
                ", rrp='" + rrp + '\'' +
                ", productId=" + productId +
                '}';
    }
}
