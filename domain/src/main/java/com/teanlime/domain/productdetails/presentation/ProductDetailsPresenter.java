package com.teanlime.domain.productdetails.presentation;

import com.teanlime.domain.api.presentation.Presenter;
import com.teanlime.domain.api.usecase.UseCaseCallback;
import com.teanlime.domain.productdetails.model.response.ProductDetails;
import com.teanlime.domain.productdetails.usecase.GetProductDetailsUseCase;

public class ProductDetailsPresenter extends Presenter<ProductDetailsView> {

    private final GetProductDetailsUseCase getProductDetailsUseCase;
    private final String productId;

    protected ProductDetailsPresenter(GetProductDetailsUseCase getProductDetailsUseCase,
                                      ProductDetailsView emptyView,
                                      String productId) {
        super(emptyView);

        this.getProductDetailsUseCase = getProductDetailsUseCase;
        this.productId = productId;
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
}
