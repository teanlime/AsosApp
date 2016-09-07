package com.teanlime.asosapp.productdetails.di;

import android.support.v4.app.Fragment;

import com.teanlime.asosapp.productdetails.presentation.ProductDetailsFragment;
import com.teanlime.asosapp.utils.di.dagger.scope.PerFragment;
import com.teanlime.data.api.remote.DecoratedRetrofitRemoteService;
import com.teanlime.data.api.remote.RemoteService;
import com.teanlime.data.api.remote.rest.retrofit.RetrofitRemoteRestService;

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
}
