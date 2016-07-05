package com.teanlime.data.categories.model.repository.local;

import com.teanlime.domain.categories.model.request.CategoriesGroup;
import com.teanlime.domain.categories.model.response.Categories;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;

public class CategoriesInMemoryLocalRepository implements CategoriesLocalRepository {

    private Map<CategoriesGroup, Categories> categoriesMap = new HashMap<>();

    @Override
    public void putCategoriesForGroup(CategoriesGroup categoriesGroup, Categories categories) {
        categoriesMap.put(categoriesGroup, categories);
    }

    @Override
    public void eraseCategoryForGroup(CategoriesGroup categoriesGroup) {
        categoriesMap.remove(categoriesGroup);
    }

    @Override
    public Observable<Categories> getCategoriesForGroup(CategoriesGroup categoriesGroup) {
        return Observable.create(subscriber -> {
            if (hasCategoriesForGroup(categoriesGroup)) {
                subscriber.onNext(categoriesMap.get(categoriesGroup));
                subscriber.onCompleted();
            } else {
                subscriber.onError(new NullPointerException("Data is null!"));
            }
        });
    }

    @Override
    public boolean hasCategoriesForGroup(CategoriesGroup categoriesGroup) {
        return categoriesMap.containsKey(categoriesGroup);
    }
}
