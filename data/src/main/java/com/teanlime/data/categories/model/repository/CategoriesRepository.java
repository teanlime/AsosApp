package com.teanlime.data.categories.model.repository;

import com.teanlime.domain.categories.model.request.CategoriesGroup;
import com.teanlime.domain.categories.model.response.Categories;

import rx.Observable;

public interface CategoriesRepository {

    Observable<Categories> getCategoriesForGroup(CategoriesGroup categoriesGroup);
}
