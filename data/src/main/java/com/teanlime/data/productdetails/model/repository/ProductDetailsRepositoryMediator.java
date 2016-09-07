package com.teanlime.data.productdetails.model.repository;

import com.annimon.stream.Optional;
import com.teanlime.data.productdetails.model.repository.local.ProductDetailsLocalRepository;
import com.teanlime.domain.productdetails.model.response.ProductDetails;

import rx.Observable;

import static com.teanlime.domain.api.util.Validate.nonNull;

public class ProductDetailsRepositoryMediator implements ProductDetailsRepository {

    private final ProductDetailsLocalRepository localProductDetailsRepository;
    private final ProductDetailsRepository remoteProductDetailsRepository;

    public ProductDetailsRepositoryMediator(ProductDetailsLocalRepository localProductDetailsRepository,
                                            ProductDetailsRepository remoteProductDetailsRepository) {

        this.remoteProductDetailsRepository = nonNull(remoteProductDetailsRepository);
        this.localProductDetailsRepository = nonNull(localProductDetailsRepository);

    }

    @Override
    public Observable<Optional<ProductDetails>> getProductDetails() {
        if (localProductDetailsRepository.hasProductDetails()) {
            return localProductDetailsRepository.getProductDetails();
        } else {
            return remoteProductDetailsRepository.getProductDetails()
                    .doOnNext(productDetailsOptional ->
                            productDetailsOptional.ifPresent(localProductDetailsRepository::putProductDetails));
        }
    }
}
