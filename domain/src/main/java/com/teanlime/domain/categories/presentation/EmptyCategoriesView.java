package com.teanlime.domain.categories.presentation;

import com.teanlime.domain.categories.model.response.Categories;

public class EmptyCategoriesView implements CategoriesView {

    @Override
    public void displayCategories(Categories model) {

    }

    @Override
    public void displayCategoriesError(String exception) {

    }
}
