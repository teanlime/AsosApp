package com.teanlime.data.categories.model.repository;

import com.annimon.stream.Optional;
import com.annimon.stream.function.Consumer;
import com.teanlime.data.categories.model.repository.local.CategoriesLocalRepository;
import com.teanlime.domain.categories.model.request.CategoriesGroup;
import com.teanlime.domain.categories.model.response.Categories;

import rx.Observable;
import rx.functions.Action1;

import static com.teanlime.domain.api.util.Validate.nonNull;

/**
 * Mediates the data between local and remote storage. If requested data is already cached locally,
 * it will be retried from that cache. Otherwise, remote repository is called and data is locally stored one retrieved
 */
public class CategoriesRepositoryMediator implements CategoriesRepository {

    private final CategoriesLocalRepository localCategoriesRepository;
    private final CategoriesRepository remoteCategoriesRepository;

    public CategoriesRepositoryMediator(CategoriesLocalRepository localCategoriesRepository,
                                        CategoriesRepository remoteCategoriesRepository) {

        this.remoteCategoriesRepository = nonNull(remoteCategoriesRepository);
        this.localCategoriesRepository = nonNull(localCategoriesRepository);

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
