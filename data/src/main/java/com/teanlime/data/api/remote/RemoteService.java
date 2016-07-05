package com.teanlime.data.api.remote;

import com.teanlime.data.categories.model.response.CategoriesModel;

import rx.Observable;

public interface RemoteService {

    Observable<CategoriesModel> getCategories(String categoriesGroup);
}
