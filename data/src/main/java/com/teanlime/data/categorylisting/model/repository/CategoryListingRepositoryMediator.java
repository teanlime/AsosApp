package com.teanlime.data.categorylisting.model.repository;

import com.annimon.stream.Optional;
import com.teanlime.data.categorylisting.model.repository.local.CategoryListingLocalRepository;
import com.teanlime.domain.categorylisting.model.response.CategoryListing;

import rx.Observable;

import static com.teanlime.domain.api.util.Validate.nonNull;

public class CategoryListingRepositoryMediator implements CategoryListingRepository {

    private final CategoryListingLocalRepository localCategoryListingRepository;
    private final CategoryListingRepository remoteCategoryListingRepository;

    public CategoryListingRepositoryMediator(CategoryListingLocalRepository localCategoryListingRepository,
                                             CategoryListingRepository remoteCategoryListingRepository) {

        this.remoteCategoryListingRepository = nonNull(remoteCategoryListingRepository);
        this.localCategoryListingRepository = nonNull(localCategoryListingRepository);

    }

    @Override
    public Observable<Optional<CategoryListing>> getCategoryListing() {
        if (localCategoryListingRepository.hasCategoryListing()) {
            return localCategoryListingRepository.getCategoryListing();
        } else {
            return remoteCategoryListingRepository.getCategoryListing()
                    .doOnNext(categoryListingOptional ->
                            categoryListingOptional.ifPresent(localCategoryListingRepository::putCategoryListing));
        }
    }
}
