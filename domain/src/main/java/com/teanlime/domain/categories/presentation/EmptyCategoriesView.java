package com.teanlime.domain.categories.presentation;

import com.teanlime.domain.categories.model.response.Categories;

import javax.inject.Inject;

class EmptyCategoriesView implements CategoriesView {

    @Inject
    EmptyCategoriesView() {

    }

    @Override
    public void displayCategories(Categories model) {

    }

    @Override
    public void displayCategoriesError(String exception) {

    }

    @Override
    public void displayLoading() {

    }

    @Override
    public void initViews() {

    }
}
