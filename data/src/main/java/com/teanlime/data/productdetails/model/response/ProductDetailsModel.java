package com.teanlime.data.productdetails.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductDetailsModel {

    @SerializedName("AssociatedProducts")
    private final List<ProductDetailsModel> associatedProducts;

    @SerializedName("Variants")
    private final List<ProductDetailsModel> variants;

    @SerializedName("ProductImageUrls")
    private final List<String> productImageUrls;

    @SerializedName("AdditionalInfo")
    private final String additionalInfo;

    @SerializedName("PreviousPrice")
    private final String previousPrice;

    @SerializedName("CurrentPrice")
    private final String currentPrice;

    @SerializedName("Description")
    private final String description;

    @SerializedName("BasePrice")
    private final Double basePrice;

    @SerializedName("PriceType")
    private final String priceType;

    @SerializedName("ProductId")
    private final Long productId;

    @SerializedName("CareInfo")
    private final String careInfo;

    @SerializedName("InStock")
    private final Boolean inStock;

    @SerializedName("IsInSet")
    private final Boolean inSet;

    @SerializedName("Colour")
    private final Object colour;

    @SerializedName("Brand")
    private final String brand;

    @SerializedName("Title")
    private final String title;

    @SerializedName("Size")
    private final Object size;

    @SerializedName("Sku")
    private final String sku;

    @SerializedName("RRP")
    private final String rrp;

    public ProductDetailsModel(List<ProductDetailsModel> associatedProducts,
                               List<ProductDetailsModel> variants,
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

    public List<ProductDetailsModel> getAssociatedProducts() {
        return associatedProducts;
    }

    public List<ProductDetailsModel> getVariants() {
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
