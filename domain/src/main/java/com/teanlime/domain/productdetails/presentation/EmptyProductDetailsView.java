package com.teanlime.domain.productdetails.presentation;

import com.teanlime.domain.productdetails.model.response.ProductDetails;

public class EmptyProductDetailsView implements ProductDetailsView {

    @Override
    public void setupViews() {

    }

    @Override
    public void displayLoading() {

    }

    @Override
    public void displayError(String exception) {

    }

    @Override
    public void displayContent(ProductDetails model) {

    }
}
