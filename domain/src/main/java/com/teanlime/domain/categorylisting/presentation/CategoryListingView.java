package com.teanlime.domain.categorylisting.presentation;

import com.teanlime.domain.categorylisting.model.response.CategoryListing;

public interface CategoryListingView {

    void setupViews();

    void displayLoading();

    void displayError(String exception);

    void displayContent(CategoryListing model);
}
