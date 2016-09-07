package com.teanlime.domain.categorylisting.presentation;

import com.teanlime.domain.api.presentation.Presenter;
import com.teanlime.domain.api.usecase.UseCaseCallback;
import com.teanlime.domain.categorylisting.model.response.CategoryListing;
import com.teanlime.domain.categorylisting.usecase.GetCategoryListingUseCase;

import javax.inject.Inject;

public class CategoryListingPresenter extends Presenter<CategoryListingView> {

    private final GetCategoryListingUseCase getCategoryListingUseCase;

    @Inject
    public CategoryListingPresenter(GetCategoryListingUseCase getCategoryListingUseCase,
                                    EmptyCategoryListingView emptyView) {
        super(emptyView);
        this.getCategoryListingUseCase = getCategoryListingUseCase;
    }

    public void onViewCreated() {
        view.setupViews();
        view.displayLoading();

        requestCategoryListingData();
    }

    private void requestCategoryListingData() {
        getCategoryListingUseCase.execute(new UseCaseCallback<CategoryListing, String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(String exception) {
                view.displayError(exception);
            }

            @Override
            public void onNext(CategoryListing model) {
                view.displayContent(model);
            }
        });
    }

    public void onProductClicked(final int productPosition) {
        getCategoryListingUseCase.execute(new UseCaseCallback<CategoryListing, String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(String exception) {
                view.displayError(exception);
            }

            @Override
            public void onNext(CategoryListing model) {
                view.navigateToPositionDetails(model.getListings().get(productPosition).getProductId());
            }
        });
    }
}
