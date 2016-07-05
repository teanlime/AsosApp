package com.teanlime.data.categories.remote;

import com.teanlime.data.api.remote.RemoteRepository;
import com.teanlime.data.api.remote.RemoteService;
import com.teanlime.data.categories.model.response.CategoriesModel;

import rx.Observable;

public class GetWomenCategoriesRemoteRepository implements RemoteRepository<CategoriesModel> {

    private static final String WOMEN = "women";

    private final RemoteService service;

    public GetWomenCategoriesRemoteRepository(RemoteService service) {
        this.service = service;
    }

    @Override
    public Observable<CategoriesModel> getData() {
        return service.getCategories(WOMEN);
    }
}

