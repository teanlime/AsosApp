package com.teanlime.data.categorylisting.usecase;

import com.teanlime.data.api.usecase.RxUseCaseSubscription;
import com.teanlime.data.categorylisting.model.repository.CategoryListingRepository;
import com.teanlime.domain.api.usecase.UseCaseCallback;
import com.teanlime.domain.categorylisting.model.response.CategoryListing;
import com.teanlime.domain.categorylisting.usecase.GetCategoryListingUseCase;

import static com.teanlime.domain.api.util.Validate.nonNull;

/**
 * Returns Categories from a repository
 */
public class GetCategoryListingRepositoryUseCase implements GetCategoryListingUseCase {

    private final RxUseCaseSubscription<CategoryListing, String> rxUseCaseSubscription;
    private final CategoryListingRepository categoryListingRepository;

    public GetCategoryListingRepositoryUseCase(RxUseCaseSubscription<CategoryListing, String> rxUseCaseSubscription,
                                               CategoryListingRepository categoryListingRepository) {

        this.categoryListingRepository = nonNull(categoryListingRepository);
        this.rxUseCaseSubscription = nonNull(rxUseCaseSubscription);
    }

    @Override
    public void execute(UseCaseCallback<CategoryListing, String> callback) {
        rxUseCaseSubscription.subscribe(categoryListingRepository.getCategoryListing(), callback);
    }

    @Override
    public void cancel() {
        rxUseCaseSubscription.cancel();
    }
}
