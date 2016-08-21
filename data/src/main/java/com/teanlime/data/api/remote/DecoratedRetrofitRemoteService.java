package com.teanlime.data.api.remote;

import com.teanlime.data.api.remote.rest.retrofit.RetrofitRemoteRestService;
import com.teanlime.data.categories.model.response.CategoriesModel;

import rx.Observable;

public class DecoratedRetrofitRemoteService implements RemoteService {

    private final RetrofitRemoteRestService retrofitRemoteRestService;

    public DecoratedRetrofitRemoteService(RetrofitRemoteRestService retrofitRemoteRestService) {
        this.retrofitRemoteRestService = retrofitRemoteRestService;
    }

    @Override
    public Observable<CategoriesModel> getCategories(String categoriesGroupQuery) {
        return retrofitRemoteRestService.getCategories(categoriesGroupQuery);
    }
}
