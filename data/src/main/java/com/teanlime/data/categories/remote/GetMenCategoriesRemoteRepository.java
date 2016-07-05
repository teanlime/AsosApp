package com.teanlime.data.categories.remote;

import com.teanlime.data.api.remote.RemoteRepository;
import com.teanlime.data.api.remote.RemoteService;
import com.teanlime.data.categories.model.response.CategoriesModel;

import rx.Observable;

public class GetMenCategoriesRemoteRepository implements RemoteRepository<CategoriesModel> {

    private static final String MEN = "men";

    private final RemoteService service;

    public GetMenCategoriesRemoteRepository(RemoteService service) {
        this.service = service;
    }

    @Override
    public Observable<CategoriesModel> getData() {
        return service.getCategories(MEN);
    }
}
