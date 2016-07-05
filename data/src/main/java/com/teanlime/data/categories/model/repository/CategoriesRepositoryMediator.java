package com.teanlime.data.categories.model.repository;

import com.teanlime.data.categories.model.repository.local.CategoriesLocalRepository;
import com.teanlime.domain.categories.model.request.CategoriesGroup;
import com.teanlime.domain.categories.model.response.Categories;

import rx.Observable;

public class CategoriesRepositoryMediator implements CategoriesRepository {

    private final CategoriesLocalRepository localCategoriesRepository;
    private final CategoriesRepository remoteCategoriesRepository;

    public CategoriesRepositoryMediator(CategoriesLocalRepository localCategoriesRepository,
                                        CategoriesRepository remoteCategoriesRepository) {
        this.remoteCategoriesRepository = remoteCategoriesRepository;
        this.localCategoriesRepository = localCategoriesRepository;

    }

    @Override
    public Observable<Categories> getCategoriesForGroup(CategoriesGroup categoriesGroup) {
        if (localCategoriesRepository.hasCategoriesForGroup(categoriesGroup)) {
            return localCategoriesRepository.getCategoriesForGroup(categoriesGroup);
        } else {
            return remoteCategoriesRepository.getCategoriesForGroup(categoriesGroup)
                    .doOnNext(categories -> localCategoriesRepository.putCategoriesForGroup(categoriesGroup, categories));
        }
    }
}
