package com.teanlime.domain.categories.presentation;

import com.teanlime.domain.categories.model.response.Categories;

public interface CategoriesView {

    void displayCategories(Categories model);

    void displayCategoriesError(String exception);

    void displayLoading();

    void initViews();
}
