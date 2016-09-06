package com.teanlime.data.api.remote;

import com.teanlime.data.api.remote.rest.retrofit.RetrofitRemoteRestService;
import com.teanlime.data.categories.model.response.CategoriesModel;
import com.teanlime.data.categorylisting.model.response.CategoryListingModel;

import rx.Observable;

import static com.teanlime.domain.api.util.Validate.nonNull;

/**
 * Encapsulates Retrofit calls.
 * Wraps and delegates all RetrofitRemoteRestService calls and responses and returns them as RemoteService
 * response
 */
public class DecoratedRetrofitRemoteService implements RemoteService {

    private final RetrofitRemoteRestService retrofitRemoteRestService;

    public DecoratedRetrofitRemoteService(RetrofitRemoteRestService retrofitRemoteRestService) {
        this.retrofitRemoteRestService = nonNull(retrofitRemoteRestService);
    }

    @Override
    public Observable<CategoriesModel> getCategories(String categoriesGroupQuery) {
        return retrofitRemoteRestService.getCategories(categoriesGroupQuery);
    }

    @Override
    public Observable<CategoryListingModel> getCategoryListing(String categoryId) {
        return retrofitRemoteRestService.getCategoryListing(categoryId);
    }
}
