package com.teanlime.asosapp.application.di;

import com.google.gson.Gson;
import com.teanlime.data.api.remote.rest.retrofit.RetrofitRemoteRestService;
import com.teanlime.data.categories.model.response.CategoriesModel;

import java.util.concurrent.TimeUnit;

import retrofit2.http.Path;
import rx.Observable;

public class TestRetrofitRemoteRestService implements RetrofitRemoteRestService {

    @Override
    public Observable<CategoriesModel> getCategories(@Path("categories_group") String categoriesGroupQuery) {
        final CategoriesModel weatherData = new Gson().fromJson(TestCategoriesModel.CATEGORIES_MODEL_JSON,
                CategoriesModel.class);

        return Observable.just(weatherData).delay(1, TimeUnit.SECONDS);

    }
}
