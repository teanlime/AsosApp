package com.teanlime.data.categories.model.repository;

import com.annimon.stream.Optional;
import com.teanlime.domain.categories.model.request.CategoriesGroup;
import com.teanlime.domain.categories.model.response.Categories;

import rx.Observable;

/**
 * Repository for retrieving categories group data
 */
public interface CategoriesRepository {

    /**
     * Retrieves categories
     *
     * @param categoriesGroup category group key
     * @return categories data
     */
    Observable<Optional<Categories>> getCategoriesForGroup(CategoriesGroup categoriesGroup);
}
