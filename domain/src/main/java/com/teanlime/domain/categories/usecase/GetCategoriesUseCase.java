package com.teanlime.domain.categories.usecase;

import com.teanlime.domain.api.usecase.UseCase;
import com.teanlime.domain.categories.model.request.CategoriesGroup;
import com.teanlime.domain.categories.model.response.Categories;

public interface GetCategoriesUseCase extends UseCase<Categories, String> {

    void categoriesGroup(CategoriesGroup categoriesGroup);
}
