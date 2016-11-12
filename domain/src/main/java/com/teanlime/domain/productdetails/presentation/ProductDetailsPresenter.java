package com.teanlime.domain.productdetails.presentation;

import com.teanlime.domain.api.presentation.Presenter;
import com.teanlime.domain.api.usecase.UseCaseCallback;
import com.teanlime.domain.productdetails.model.response.ProductDetails;
import com.teanlime.domain.productdetails.usecase.AddToFavouritesUseCase;
import com.teanlime.domain.productdetails.usecase.GetProductDetailsUseCase;

import javax.inject.Inject;

public class ProductDetailsPresenter extends Presenter<ProductDetailsView> {

    private final GetProductDetailsUseCase getProductDetailsUseCase;
    private final AddToFavouritesUseCase addToFavouritesUseCase;

    @Inject
    protected ProductDetailsPresenter(GetProductDetailsUseCase getProductDetailsUseCase,
                                      EmptyProductDetailsView emptyView,
                                      AddToFavouritesUseCase addToFavouritesUseCase) {
        super(emptyView);

        this.getProductDetailsUseCase = getProductDetailsUseCase;
        this.addToFavouritesUseCase = addToFavouritesUseCase;
    }

    public void onViewCreated() {
        view.setupViews();
        view.displayLoading();

        requestProductDetails();
    }

    private void requestProductDetails() {
        getProductDetailsUseCase.execute(new UseCaseCallback<ProductDetails, String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(String exception) {
                view.displayError(exception);
            }

            @Override
            public void onNext(ProductDetails model) {
                view.displayContent(model);
            }
        });
    }

    public void onFavouriteClicked(String listingId) {
        addToFavouritesUseCase.execute(new UseCaseCallback<Void, String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(String exception) {
                view.displayErrorAddingFavourite(exception);
            }

            @Override
            public void onNext(Void model) {
                view.displayItemAddedTOFavouritesSuccessfully();
            }
        });
    }
}
