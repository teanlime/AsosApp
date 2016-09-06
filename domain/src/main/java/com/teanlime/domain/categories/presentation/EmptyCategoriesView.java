package com.teanlime.domain.categories.presentation;

import com.teanlime.domain.categories.model.response.Category;

import java.util.List;

import javax.inject.Inject;

class EmptyCategoriesView implements CategoriesView {

    @Inject
    EmptyCategoriesView() {

    }

    @Override
    public void setNavigationDrawerSubmenuCategories(String categoriesGroup, List<Category> categoryList) {

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
    public void setupViews() {

    }

    @Override
    public void closeNavigationDrawer() {

    }

    @Override
    public void processOnBackPressed() {

    }

    @Override
    public void selectWomenCategoryGroup() {

    }

    @Override
    public void deselectMenCategoryGroup() {

    }

    @Override
    public void selectMenCategoryGroup() {

    }

    @Override
    public void deselectWomenCategoryGroup() {

    }

    @Override
    public void startCategoryFragment(String categoryId) {

    }

    @Override
    public void setSelectedCategoriesGroup(String categoriesGroup) {

    }

    @Override
    public boolean isLollipop() {
        return false;
    }

    @Override
    public void deselectMenCategoryGroupLollipopExtra() {

    }

    @Override
    public void deselectWomenCategoryGroupLollipopExtra() {

    }

    @Override
    public void selectMenCategoryGroupLollipopExtra() {

    }

    @Override
    public void selectWomenCategoryGroupLollipopExtra() {

    }
}
