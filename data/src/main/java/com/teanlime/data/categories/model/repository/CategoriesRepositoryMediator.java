package com.teanlime.data.categories.model.repository;

import com.annimon.stream.Optional;
import com.annimon.stream.function.Consumer;
import com.teanlime.data.categories.model.repository.local.CategoriesLocalRepository;
import com.teanlime.domain.categories.model.request.CategoriesGroup;
import com.teanlime.domain.categories.model.response.Categories;

import rx.Observable;
import rx.functions.Action1;

public class CategoriesRepositoryMediator implements CategoriesRepository {

    private final CategoriesLocalRepository localCategoriesRepository;
    private final CategoriesRepository remoteCategoriesRepository;

    public CategoriesRepositoryMediator(CategoriesLocalRepository localCategoriesRepository,
                                        CategoriesRepository remoteCategoriesRepository) {
        this.remoteCategoriesRepository = remoteCategoriesRepository;
        this.localCategoriesRepository = localCategoriesRepository;

    }

    @Override
    public Observable<Optional<Categories>> getCategoriesForGroup(final CategoriesGroup categoriesGroup) {
        if (localCategoriesRepository.hasCategoriesForGroup(categoriesGroup)) {
            return localCategoriesRepository.getCategoriesForGroup(categoriesGroup);
        } else {
            // TODO #92 Issue with Jack, cannot use Lambda if using class variable
            return remoteCategoriesRepository.getCategoriesForGroup(categoriesGroup)
                    .doOnNext(new Action1<Optional<Categories>>() {
                        @Override
                        public void call(Optional<Categories> categoriesOptional) {
                            categoriesOptional.ifPresent(new Consumer<Categories>() {
                                @Override
                                public void accept(Categories categories) {
                                    localCategoriesRepository.putCategoriesForGroup(categoriesGroup, categories);
                                }
                            });
                        }
                    });
        }
    }
}
