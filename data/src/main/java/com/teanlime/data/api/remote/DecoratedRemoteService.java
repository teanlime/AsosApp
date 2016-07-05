package com.teanlime.data.api.remote;

import com.teanlime.data.categories.model.response.CategoriesModel;

import rx.Observable;

public class DecoratedRemoteService implements RemoteService {

    private final RemoteService remoteService;

    public DecoratedRemoteService(RemoteService remoteService) {
        this.remoteService = remoteService;
    }

    @Override
    public Observable<CategoriesModel> getCategories(String categoriesGroupQuery) {
        return remoteService.getCategories(categoriesGroupQuery);
    }
}
