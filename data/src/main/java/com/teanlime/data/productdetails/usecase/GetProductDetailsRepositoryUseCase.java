package com.teanlime.data.productdetails.usecase;

import com.teanlime.data.api.usecase.RxUseCaseSubscription;
import com.teanlime.data.productdetails.model.repository.ProductDetailsRepository;
import com.teanlime.domain.api.usecase.UseCaseCallback;
import com.teanlime.domain.productdetails.model.response.ProductDetails;
import com.teanlime.domain.productdetails.usecase.GetProductDetailsUseCase;

import static com.teanlime.domain.api.util.Validate.nonNull;

/**
 * Returns Categories from a repository
 */
public class GetProductDetailsRepositoryUseCase implements GetProductDetailsUseCase {

    private final RxUseCaseSubscription<ProductDetails, String> rxUseCaseSubscription;
    private final ProductDetailsRepository productDetailsRepository;

    public GetProductDetailsRepositoryUseCase(RxUseCaseSubscription<ProductDetails, String> rxUseCaseSubscription,
                                              ProductDetailsRepository productDetailsRepository) {

        this.productDetailsRepository = nonNull(productDetailsRepository);
        this.rxUseCaseSubscription = nonNull(rxUseCaseSubscription);
    }

    @Override
    public void execute(UseCaseCallback<ProductDetails, String> callback) {
        rxUseCaseSubscription.subscribe(productDetailsRepository.getProductDetails(), callback);
    }

    @Override
    public void cancel() {
        rxUseCaseSubscription.cancel();
    }
}
