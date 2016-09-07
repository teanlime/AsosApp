package com.teanlime.domain.productdetails.presentation;

import com.teanlime.domain.productdetails.model.response.ProductDetails;

public interface ProductDetailsView {
    void setupViews();

    void displayLoading();

    void displayError(String exception);

    void displayContent(ProductDetails model);
}
