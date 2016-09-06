package com.teanlime.asosapp.categorylisting.di;

import android.support.v4.app.Fragment;

import com.teanlime.asosapp.categorylisting.presentation.CategoryListingFragment;
import com.teanlime.asosapp.utils.di.dagger.scope.PerFragment;
import com.teanlime.data.api.mapper.ListMapper;
import com.teanlime.data.api.mapper.Mapper;
import com.teanlime.data.api.mapper.StringUseCaseExceptionMapper;
import com.teanlime.data.api.remote.DecoratedRetrofitRemoteService;
import com.teanlime.data.api.remote.RemoteService;
import com.teanlime.data.api.remote.rest.retrofit.RetrofitRemoteRestService;
import com.teanlime.data.api.usecase.CallbackDelegateSubscriberFactory;
import com.teanlime.data.api.usecase.RxUseCaseSubscription;
import com.teanlime.data.api.usecase.SubscriberFactory;
import com.teanlime.data.categorylisting.model.repository.CategoryListingRepository;
import com.teanlime.data.categorylisting.model.repository.CategoryListingRepositoryMediator;
import com.teanlime.data.categorylisting.model.repository.local.CategoryListingInMemoryLocalRepository;
import com.teanlime.data.categorylisting.model.repository.remote.CategoryListingRemoteRepository;
import com.teanlime.data.categorylisting.model.response.CategoryListingModel;
import com.teanlime.data.categorylisting.model.response.FacetValuesModel;
import com.teanlime.data.categorylisting.model.response.FacetsModel;
import com.teanlime.data.categorylisting.model.response.ListingsModel;
import com.teanlime.data.categorylisting.model.response.mapper.CategoryListingMapper;
import com.teanlime.data.categorylisting.model.response.mapper.FacetsMapper;
import com.teanlime.data.categorylisting.model.response.mapper.FacetsValuesMapper;
import com.teanlime.data.categorylisting.model.response.mapper.ListingsMapper;
import com.teanlime.data.categorylisting.usecase.GetCategoryListingRepositoryUseCase;
import com.teanlime.domain.categorylisting.model.response.CategoryListing;
import com.teanlime.domain.categorylisting.model.response.FacetValues;
import com.teanlime.domain.categorylisting.model.response.Facets;
import com.teanlime.domain.categorylisting.model.response.Listings;
import com.teanlime.domain.categorylisting.usecase.GetCategoryListingUseCase;

import dagger.Module;
import dagger.Provides;

@Module
public class CategoryListingFragmentModule {

    private final CategoryListingFragment fragment;
    private final String categoryId;

    public CategoryListingFragmentModule(Fragment fragment, String categoryId) {
        this.fragment = (CategoryListingFragment) fragment;
        this.categoryId = categoryId;
    }

    @PerFragment
    @Provides
    CategoryListingFragment categoryListingFragment() {
        return fragment;
    }

    @PerFragment
    @Provides
    String categoryId() {
        return categoryId;
    }

    @PerFragment
    @Provides
    RemoteService decoratedRetrofitRemoteService(RetrofitRemoteRestService retrofitRemoteRestService) {
        return new DecoratedRetrofitRemoteService(retrofitRemoteRestService);
    }

    @PerFragment
    @Provides
    GetCategoryListingUseCase getCategoryListingUseCase(
            RxUseCaseSubscription<CategoryListing, String> rxUseCaseSubscription,
            CategoryListingRepository categoryListingRepository) {
        return new GetCategoryListingRepositoryUseCase(rxUseCaseSubscription, categoryListingRepository);
    }

    @PerFragment
    @Provides
    SubscriberFactory<CategoryListing, String> subscriberFactory(Mapper<Throwable, String> useCaseExceptionMapper) {
        return new CallbackDelegateSubscriberFactory<>(useCaseExceptionMapper);
    }

    @PerFragment
    @Provides
    Mapper<Throwable, String> useCaseExceptionMapper() {
        return new StringUseCaseExceptionMapper();
    }

    @PerFragment
    @Provides
    CategoryListingRepository categoryListingRepository(
            CategoryListingInMemoryLocalRepository categoryListingInMemoryLocalRepository,
            CategoryListingRemoteRepository categoryListingRemoteRepository) {
        return new CategoryListingRepositoryMediator(categoryListingInMemoryLocalRepository, categoryListingRemoteRepository);
    }

    @PerFragment
    @Provides
    Mapper<CategoryListingModel, CategoryListing> categoryListingMapper(ListMapper<ListingsModel, Listings> listingsMapper,
                                                                        ListMapper<FacetsModel, Facets> facetsMapper) {
        return new CategoryListingMapper(listingsMapper, facetsMapper);
    }

    @PerFragment
    @Provides
    ListMapper<ListingsModel, Listings> listingsMapper(ListingsMapper listingsMapper) {
        return new ListMapper<>(listingsMapper);
    }

    @PerFragment
    @Provides
    ListMapper<FacetsModel, Facets> facetsMapper(FacetsMapper facetsMapper) {
        return new ListMapper<>(facetsMapper);
    }

    @PerFragment
    @Provides
    ListMapper<FacetValuesModel, FacetValues> facetValuesMapper(FacetsValuesMapper facetValuesMapper) {
        return new ListMapper<>(facetValuesMapper);
    }
}
