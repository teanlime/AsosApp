package com.teanlime.data.categorylisting.model.repository;

import com.annimon.stream.Optional;
import com.teanlime.domain.categorylisting.model.response.CategoryListing;

import rx.Observable;

public interface CategoryListingRepository {

    Observable<Optional<CategoryListing>> getCategoryListing();
}
