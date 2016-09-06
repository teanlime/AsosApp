package com.teanlime.data.categorylisting.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.List;

public class ListingsModel {

    @SerializedName("ProductImageUrl")
    @Expose
    private final List<String> productImageUrl;

    @SerializedName("BasePrice")
    @Expose
    private final BigDecimal basePrice;

    @SerializedName("HasMoreColours")
    @Expose
    private final Boolean hasMoreColours;
    @SerializedName("isInSet")
    @Expose
    private final Boolean inSet;

    @SerializedName("PreviousPrice")
    @Expose
    private final String previousPrice;
    @SerializedName("CurrentPrice")
    @Expose
    private final String currentPrice;
    @SerializedName("Title")
    @Expose
    private final String title;
    @SerializedName("Brand")
    @Expose
    private final String brand;
    @SerializedName("RRP")
    @Expose
    private final String rrp;

    @SerializedName("ProductId")
    @Expose
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
