package com.teanlime.data.categorylisting.model.repository.local;

import com.teanlime.data.categorylisting.model.repository.CategoryListingRepository;
import com.teanlime.domain.categorylisting.model.response.CategoryListing;

public interface CategoryListingLocalRepository extends CategoryListingRepository {

    void putCategoryListing(CategoryListing categoryListing);

    boolean hasCategoryListing();

    void eraseCategoryListingFor();
}
