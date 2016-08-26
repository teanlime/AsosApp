package com.teanlime.domain.categories.usecase;

import com.teanlime.domain.api.usecase.UseCase;
import com.teanlime.domain.categories.model.request.CategoriesGroup;
import com.teanlime.domain.categories.model.response.Categories;

/**
 * Used for retrieving of the Categories object
 * Can amend the request
 */
public interface GetCategoriesUseCase extends UseCase<Categories, String> {

    /**
     * Sets the CategoriesGroup on the request
     *
     * @param categoriesGroup categories group for retrieveal of Categories
     * @return this
     */
    GetCategoriesUseCase categoriesGroup(CategoriesGroup categoriesGroup);
}
