package com.teanlime.domain.category.model.response;

import java.math.BigDecimal;
import java.util.List;

public class Listings {

    private final List<String> productImageUrl;

    private final BigDecimal basePrice;

    private final Boolean hasMoreColours;
    private final Boolean inSet;

    private final String previousPrice;
    private final String currentPrice;
    private final String title;
    private final String brand;
    private final String rrp;

    private final Long productId;

    public Listings(List<String> productImageUrl,
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
        return "Listings{" +
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
