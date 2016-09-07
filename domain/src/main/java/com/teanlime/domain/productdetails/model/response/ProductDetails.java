package com.teanlime.domain.productdetails.model.response;

import java.util.List;

public class ProductDetails {

    private final List<ProductDetails> associatedProducts;

    private final List<ProductDetails> variants;

    private final List<String> productImageUrls;

    private final String additionalInfo;

    private final String previousPrice;

    private final String currentPrice;

    private final String description;

    private final Double basePrice;

    private final String priceType;

    private final Long productId;

    private final String careInfo;

    private final Boolean inStock;

    private final Boolean inSet;

    private final Object colour;

    private final String brand;

    private final String title;

    private final Object size;

    private final String sku;

    private final String rrp;

    public ProductDetails(List<ProductDetails> associatedProducts,
                          List<ProductDetails> variants,
                          List<String> productImageUrls,
                          String additionalInfo,
                          String previousPrice,
                          String currentPrice,
                          String description,
                          Double basePrice,
                          String priceType,
                          Long productId,
                          String careInfo,
                          Boolean inStock,
                          Boolean inSet,
                          Object colour,
                          String brand,
                          String title,
                          Object size,
                          String sku,
                          String rrp) {

        this.associatedProducts = associatedProducts;
        this.productImageUrls = productImageUrls;
        this.additionalInfo = additionalInfo;
        this.previousPrice = previousPrice;
        this.currentPrice = currentPrice;
        this.description = description;
        this.basePrice = basePrice;
        this.priceType = priceType;
        this.productId = productId;
        this.careInfo = careInfo;
        this.variants = variants;
        this.inStock = inStock;
        this.colour = colour;
        this.brand = brand;
        this.title = title;
        this.inSet = inSet;
        this.size = size;
        this.sku = sku;
        this.rrp = rrp;
    }

    public List<ProductDetails> getAssociatedProducts() {
        return associatedProducts;
    }

    public List<ProductDetails> getVariants() {
        return variants;
    }

    public List<String> getProductImageUrls() {
        return productImageUrls;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public String getPreviousPrice() {
        return previousPrice;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public String getDescription() {
        return description;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public String getPriceType() {
        return priceType;
    }

    public Long getProductId() {
        return productId;
    }

    public String getCareInfo() {
        return careInfo;
    }

    public Boolean getInStock() {
        return inStock;
    }

    public Boolean getInSet() {
        return inSet;
    }

    public Object getColour() {
        return colour;
    }

    public String getBrand() {
        return brand;
    }

    public String getTitle() {
        return title;
    }

    public Object getSize() {
        return size;
    }

    public String getSku() {
        return sku;
    }

    public String getRrp() {
        return rrp;
    }
}
