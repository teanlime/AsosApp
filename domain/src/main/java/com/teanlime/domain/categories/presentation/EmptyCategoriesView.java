package com.teanlime.domain.categories.presentation;

import com.teanlime.domain.categories.model.response.Categories;

import javax.inject.Inject;

public class EmptyCategoriesView implements CategoriesView {

    @Inject
    public EmptyCategoriesView() {

    }

    @Override
    public void displayCategories(Categories model) {

    }

    @Override
    public void displayCategoriesError(String exception) {

    }
}
