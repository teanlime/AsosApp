package com.teanlime.data.api.remote.rest.retrofit;

import com.teanlime.data.categories.model.response.CategoriesModel;
import com.teanlime.data.categorylisting.model.response.CategoryListingModel;
import com.teanlime.data.productdetails.model.response.ProductDetailsModel;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Retrofit API for receiving network data. To be automatically inflated by Retrofit API
 * Do not instantiate manually
 */
public interface RetrofitRemoteRestService {

    /**
     * Returns an Observable of all clothing categories for a given category group (either Women or Men)
     *
     * @param categoriesGroupQuery super category group (for example men or women)
     * @return a list of available categories for specified group
     */
    @GET("https://dl.dropboxusercontent.com/u/1559445/ASOS/SampleApi/cats_{categories_group}.json")
    Observable<CategoriesModel> getCategories(@Path("categories_group") String categoriesGroupQuery);

    @GET("https://dl.dropboxusercontent.com/u/1559445/ASOS/SampleApi/anycat_products.json")
    Observable<CategoryListingModel> getCategoryListing(@Query("catid") String categoryId);

    @GET("https://dl.dropboxusercontent.com/u/1559445/ASOS/SampleApi/anyproduct_details.json")
    Observable<ProductDetailsModel> getProductDetails(@Query("catid") String productId);
}
