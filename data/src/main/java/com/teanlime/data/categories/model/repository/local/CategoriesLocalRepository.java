package com.teanlime.data.categories.model.repository.local;

import com.teanlime.data.categories.model.repository.CategoriesRepository;
import com.teanlime.domain.categories.model.request.CategoriesGroup;
import com.teanlime.domain.categories.model.response.Categories;

/**
 * Local caches for the Categories group data
 */
public interface CategoriesLocalRepository extends CategoriesRepository {

    /**
     * Adds new categories group to local cache
     *
     * @param categoriesGroup category group key
     * @param categories      categories group data
     */
    void putCategoriesForGroup(CategoriesGroup categoriesGroup, Categories categories);

    /**
     * Checks if category group is stored
     *
     * @param categoriesGroup categories group key
     * @return true is has categories group in local storage
     */
    boolean hasCategoriesForGroup(CategoriesGroup categoriesGroup);

    /**
     * Removes category group from locale storage
     *
     * @param categoriesGroup category group key
     */
    void eraseCategoryForGroup(CategoriesGroup categoriesGroup);
}
