package com.teanlime.data.categorylisting.model.repository.local;

import com.annimon.stream.Optional;
import com.teanlime.domain.categorylisting.model.response.CategoryListing;

import javax.inject.Inject;

import rx.Observable;

public class CategoryListingInMemoryLocalRepository implements CategoryListingLocalRepository {

    private CategoryListing categoryListing;

    @Inject
    public CategoryListingInMemoryLocalRepository() {

    }

    @Override
    public void putCategoryListing(CategoryListing categoryListing) {
        this.categoryListing = categoryListing;
    }

    @Override
    public void eraseCategoryListingFor() {
        categoryListing = null;
    }

    @Override
    public Observable<Optional<CategoryListing>> getCategoryListing() {
        return Observable.create(subscriber -> {
            if (hasCategoryListing()) {
                subscriber.onNext(Optional.of(categoryListing));
                subscriber.onCompleted();
            } else {
                subscriber.onError(new NullPointerException("Data is null!"));
            }
        });
    }

    @Override
    public boolean hasCategoryListing() {
        return categoryListing != null;
    }
}
