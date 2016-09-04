package com.teanlime.domain.categories.presentation;

import com.teanlime.domain.categories.model.response.Category;

import java.util.List;

import javax.inject.Inject;

class EmptyCategoriesView implements CategoriesView {

    @Inject
    EmptyCategoriesView() {

    }

    @Override
    public void addNavigationDrawerSubmenuCategories(String categoriesGroup, List<Category> categoryList) {

    }

    @Override
    public boolean isNavigationDrawerOpen() {
        return false;
    }

    @Override
    public void openNavigationDrawer() {

    }

    @Override
    public void displayCategoriesError(String exception) {

    }

    @Override
    public void displayLoading() {

    }

    @Override
    public void displayContent() {

    }

    @Override
    public void initViews() {

    }

    @Override
    public void closeNavigationDrawer() {

    }

    @Override
    public void processOnBackPressed() {

    }
}
