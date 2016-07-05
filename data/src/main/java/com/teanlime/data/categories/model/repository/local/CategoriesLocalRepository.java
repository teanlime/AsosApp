package com.teanlime.data.categories.model.repository.local;

import com.teanlime.data.categories.model.repository.CategoriesRepository;
import com.teanlime.domain.categories.model.request.CategoriesGroup;
import com.teanlime.domain.categories.model.response.Categories;

public interface CategoriesLocalRepository extends CategoriesRepository {

    void putCategoriesForGroup(CategoriesGroup categoriesGroup, Categories categories);

    boolean hasCategoriesForGroup(CategoriesGroup categoriesGroup);

    void eraseCategoryForGroup(CategoriesGroup categoriesGroup);
}
