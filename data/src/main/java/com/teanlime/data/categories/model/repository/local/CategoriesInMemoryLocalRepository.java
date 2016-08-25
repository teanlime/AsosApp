package com.teanlime.data.categories.model.repository.local;

import com.annimon.stream.Optional;
import com.teanlime.domain.categories.model.request.CategoriesGroup;
import com.teanlime.domain.categories.model.response.Categories;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;

/**
 * Caches categories in memory
 */
public class CategoriesInMemoryLocalRepository implements CategoriesLocalRepository {

    private final Map<CategoriesGroup, Categories> categoriesMap = new HashMap<>();

    @Override
    public void putCategoriesForGroup(CategoriesGroup categoriesGroup, Categories categories) {
        categoriesMap.put(categoriesGroup, categories);
    }

    @Override
    public void eraseCategoryForGroup(CategoriesGroup categoriesGroup) {
        categoriesMap.remove(categoriesGroup);
    }

    @Override
    public Observable<Optional<Categories>> getCategoriesForGroup(final CategoriesGroup categoriesGroup) {
        return Observable.create(subscriber -> {
            if (hasCategoriesForGroup(categoriesGroup)) {
                subscriber.onNext(Optional.of(categoriesMap.get(categoriesGroup)));
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
