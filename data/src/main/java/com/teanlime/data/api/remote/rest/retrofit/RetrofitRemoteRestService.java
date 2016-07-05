package com.teanlime.data.api.remote.rest.retrofit;

import com.teanlime.data.api.remote.RemoteService;
import com.teanlime.data.categories.model.response.CategoriesModel;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

interface RetrofitRemoteRestService extends RemoteService {

    @GET("u/1559445/ASOS/SampleApi/cats_{categories_group}.json")
    Observable<CategoriesModel> getCategories(@Path("categories_group") String categoriesGroupQuery);

    //@GET("u/1559445/ASOS/SampleApi/anycat_products.json")
    //Call<List<Product>> getCategoryProducts(@Query("catId") String categoryId);

    //@GET("u/1559445/ASOS/SampleApi/anyproduct_details.json")
    //Call<Product> getProductDetails(@Query("catId") String productId);
}
