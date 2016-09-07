package com.teanlime.domain.categorylisting.presentation;

import com.teanlime.domain.categorylisting.model.response.CategoryListing;

import javax.inject.Inject;

public class EmptyCategoryListingView implements CategoryListingView {

    @Inject
    public EmptyCategoryListingView() {
    }

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
    public void navigateToPositionDetails(Long productId) {

    }

    @Override
    public void displayContent(CategoryListing model) {

    }
}
