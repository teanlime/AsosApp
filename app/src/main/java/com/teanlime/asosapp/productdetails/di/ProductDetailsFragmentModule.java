package com.teanlime.asosapp.productdetails.di;

import android.support.v4.app.Fragment;

import com.teanlime.asosapp.productdetails.presentation.ProductDetailsFragment;
import com.teanlime.asosapp.utils.di.dagger.qualifier.repository.LocalRepository;
import com.teanlime.asosapp.utils.di.dagger.qualifier.repository.RemoteRepository;
import com.teanlime.asosapp.utils.di.dagger.qualifier.repository.RepositoryMediator;
import com.teanlime.asosapp.utils.di.dagger.scope.PerFragment;
import com.teanlime.data.api.mapper.StringUseCaseExceptionMapper;
import com.teanlime.data.api.remote.DecoratedRetrofitRemoteService;
import com.teanlime.data.api.remote.RemoteService;
import com.teanlime.data.api.remote.rest.retrofit.RetrofitRemoteRestService;
import com.teanlime.data.api.usecase.CallbackDelegateSubscriberFactory;
import com.teanlime.data.api.usecase.RxUseCaseSubscription;
import com.teanlime.data.api.usecase.SubscriberFactory;
import com.teanlime.data.productdetails.model.repository.ProductDetailsRepository;
import com.teanlime.data.productdetails.model.repository.ProductDetailsRepositoryMediator;
import com.teanlime.data.productdetails.model.repository.local.ProductDetailsInMemoryLocalRepository;
import com.teanlime.data.productdetails.model.repository.local.ProductDetailsLocalRepository;
import com.teanlime.data.productdetails.model.repository.remote.ProductDetailsRemoteRepository;
import com.teanlime.data.productdetails.model.response.mapper.ProductDetailsMapper;
import com.teanlime.data.productdetails.usecase.GetProductDetailsRepositoryUseCase;
import com.teanlime.domain.productdetails.model.response.ProductDetails;
import com.teanlime.domain.productdetails.usecase.GetProductDetailsUseCase;

import dagger.Module;
import dagger.Provides;

@Module
public class ProductDetailsFragmentModule {

    private final ProductDetailsFragment fragment;
    private final String productId;

    public ProductDetailsFragmentModule(Fragment fragment, String productId) {
        this.fragment = (ProductDetailsFragment) fragment;
        this.productId = productId;
    }

    @PerFragment
    @Provides
    ProductDetailsFragment productDetailsFragment() {
        return fragment;
    }

    @PerFragment
    @Provides
    String productId() {
        return productId;
    }

    @PerFragment
    @Provides
    RemoteService decoratedRetrofitRemoteService(RetrofitRemoteRestService retrofitRemoteRestService) {
        return new DecoratedRetrofitRemoteService(retrofitRemoteRestService);
    }

    @PerFragment
    @Provides
    GetProductDetailsUseCase getProductDetailsUseCase(RxUseCaseSubscription<ProductDetails, String> rxUseCaseSubscription,
                                                      @RepositoryMediator ProductDetailsRepositoryMediator productDetailsRepository) {
        return new GetProductDetailsRepositoryUseCase(rxUseCaseSubscription, productDetailsRepository);
    }

    @PerFragment
    @Provides
    SubscriberFactory<ProductDetails, String> subcriberFactory(StringUseCaseExceptionMapper mapper) {
        return new CallbackDelegateSubscriberFactory<>(mapper);
    }

    @PerFragment
    @Provides
    @RepositoryMediator
    ProductDetailsRepositoryMediator productDetailsRepository(@LocalRepository ProductDetailsLocalRepository
                                                                      localProductDetailsRepository,
                                                              @RemoteRepository ProductDetailsRepository
                                                                      remoteProductDetailsRepository) {
        return new ProductDetailsRepositoryMediator(localProductDetailsRepository, remoteProductDetailsRepository);
    }

    @PerFragment
    @Provides
    @LocalRepository
    ProductDetailsLocalRepository localProductDetailsRepository() {
        return new ProductDetailsInMemoryLocalRepository();
    }

    @PerFragment
    @Provides
    @RemoteRepository
    ProductDetailsRepository remoteProductDetailsRepository(ProductDetailsMapper productDetailsMapper,
                                                            RemoteService service,
                                                            String productId) {
        return new ProductDetailsRemoteRepository(productDetailsMapper, service, productId);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
