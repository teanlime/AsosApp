package com.teanlime.domain.categories.presentation;

import com.teanlime.domain.categories.model.response.Category;

import java.util.List;

public interface CategoriesView {

    void setNavigationDrawerSubmenuCategories(String categoriesGroup, List<Category> categoryList);

    boolean isNavigationDrawerOpen();

    void openNavigationDrawer();

    void displayCategoriesError(String exception);

    void displayLoading();

    void displayContent();

    void setupViews();

    void closeNavigationDrawer();

    void processOnBackPressed();

    void selectWomenCategoryGroup();

    boolean isLollipop();

    void selectWomenCategoryGroupLollipopExtra();

    void deselectMenCategoryGroup();

    void selectMenCategoryGroupLollipopExtra();

    void deselectMenCategoryGroupLollipopExtra();

    void deselectWomenCategoryGroupLollipopExtra();

    void selectMenCategoryGroup();

    void deselectWomenCategoryGroup();

    void setSelectedCategoriesGroup(String categoriesGroup);
}
