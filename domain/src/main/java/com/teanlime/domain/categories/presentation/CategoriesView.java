package com.teanlime.domain.categories.presentation;

import com.teanlime.domain.categories.model.response.Category;

import java.util.List;

public interface CategoriesView {

    void addNavigationDrawerSubmenuCategories(String categoriesGroup, List<Category> categoryList);

    boolean isNavigationDrawerOpen();

    void openNavigationDrawer();

    void displayCategoriesError(String exception);

    void displayLoading();

    void displayContent();

    void initViews();

    void closeNavigationDrawer();

    void processOnBackPressed();
}
