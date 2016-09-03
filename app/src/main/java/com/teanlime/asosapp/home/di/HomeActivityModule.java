package com.teanlime.asosapp.home.di;

import android.app.Activity;
import android.content.Context;

import com.teanlime.asosapp.application.action.AndroidRxSchedulerFactory;
import com.teanlime.asosapp.home.presentation.HomeActivity;
import com.teanlime.asosapp.utils.di.dagger.qualifier.context.ActivityContext;
import com.teanlime.asosapp.utils.di.dagger.qualifier.repository.LocalRepository;
import com.teanlime.asosapp.utils.di.dagger.qualifier.repository.RemoteRepository;
import com.teanlime.asosapp.utils.di.dagger.qualifier.repository.RepositoryMediator;
import com.teanlime.asosapp.utils.di.dagger.scope.PerActivity;
import com.teanlime.data.api.mapper.ListMapper;
import com.teanlime.data.api.mapper.Mapper;
import com.teanlime.data.api.mapper.StringUseCaseExceptionMapper;
import com.teanlime.data.api.remote.DecoratedRetrofitRemoteService;
import com.teanlime.data.api.remote.RemoteService;
import com.teanlime.data.api.remote.rest.retrofit.RetrofitRemoteRestService;
import com.teanlime.data.api.usecase.RxSchedulerFactory;
import com.teanlime.data.api.usecase.RxUseCaseSubscription;
import com.teanlime.data.categories.model.repository.CategoriesRepository;
import com.teanlime.data.categories.model.repository.CategoriesRepositoryMediator;
import com.teanlime.data.categories.model.repository.local.CategoriesInMemoryLocalRepository;
import com.teanlime.data.categories.model.repository.local.CategoriesLocalRepository;
import com.teanlime.data.categories.model.repository.remote.CategoriesRemoteRepository;
import com.teanlime.data.categories.model.request.mapper.CategoriesGroupMapper;
import com.teanlime.data.categories.model.response.CategoriesModel;
import com.teanlime.data.categories.model.response.CategoryModel;
import com.teanlime.data.categories.model.response.mapper.CategoriesMapper;
import com.teanlime.data.categories.model.response.mapper.CategoryMapper;
import com.teanlime.data.categories.usecase.GetCategoriesRepositoryUseCase;
import com.teanlime.domain.api.usecase.EmptyUseCaseCallback;
import com.teanlime.domain.api.util.StringUtils;
import com.teanlime.domain.categories.model.request.CategoriesGroup;
import com.teanlime.domain.categories.model.response.Categories;
import com.teanlime.domain.categories.model.response.Category;
import com.teanlime.domain.categories.usecase.GetCategoriesUseCase;

import java.util.ArrayList;
import java.util.Collections;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeActivityModule {

    private final HomeActivity homeActivity;

    public HomeActivityModule(Activity homeActivity) {
        this.homeActivity = (HomeActivity) homeActivity;
    }

    @PerActivity
    @Provides
    @ActivityContext
    HomeActivity homeActivity() {
        return homeActivity;
    }

    @PerActivity
    @Provides
    Context homeContext() {
        return homeActivity;
    }

    @PerActivity
    @Provides
    GetCategoriesUseCase getCategoriesUseCase(
            RxUseCaseSubscription<Categories, String> rxUseCaseSubscription,
            @RepositoryMediator CategoriesRepositoryMediator categoriesRepositoryMediator) {

        return new GetCategoriesRepositoryUseCase(
                rxUseCaseSubscription,
                categoriesRepositoryMediator);
    }

    @PerActivity
    @Provides
    Mapper<Throwable, String> stringUseCaseExceptionMapper() {
        return new StringUseCaseExceptionMapper();
    }

    @PerActivity
    @Provides
    RxSchedulerFactory schedulerFactory() {
        return new AndroidRxSchedulerFactory();
    }

    @PerActivity
    @Provides
    @RepositoryMediator
    CategoriesRepositoryMediator categoriesRepositoryMediator(
            @LocalRepository CategoriesLocalRepository localCategoriesRepository,
            @RemoteRepository CategoriesRepository remoteCategoriesRepository) {

        return new CategoriesRepositoryMediator(
                localCategoriesRepository,
                remoteCategoriesRepository);
    }

    @PerActivity
    @Provides
    @LocalRepository
    CategoriesLocalRepository categoriesLocalRepository() {
        return new CategoriesInMemoryLocalRepository();
    }

    @PerActivity
    @Provides
    @RemoteRepository
    CategoriesRepository remoteCategoriesRepository(
            Mapper<CategoriesGroup, String> categoriesGroupQueryMapper,
            Mapper<CategoriesModel, Categories> responseMapper,
            RemoteService service) {

        return new CategoriesRemoteRepository(
                categoriesGroupQueryMapper,
                responseMapper,
                StringUtils.EMPTY,
                service);
    }

    @PerActivity
    @Provides
    Mapper<CategoriesGroup, String> categoriesGroupQueryMapper() {
        return new CategoriesGroupMapper();
    }

    @PerActivity
    @Provides
    Mapper<CategoriesModel, Categories> categoriesMapper(ListMapper<CategoryModel, Category> categoryListMapper) {

        return new CategoriesMapper(
                categoryListMapper,
                Collections.unmodifiableList(new ArrayList<>()));
    }

    @PerActivity
    @Provides
    Mapper<CategoryModel, Category> categoryMapper() {
        return new CategoryMapper();
    }

    @PerActivity
    @Provides
    EmptyUseCaseCallback<Categories, String> emptyUserCallback() {
        return new EmptyUseCaseCallback<>();
    }

    @PerActivity
    @Provides
    RemoteService decoratedRetrofitRemoteService(
            RetrofitRemoteRestService retrofitRemoteRestService) {

        return new DecoratedRetrofitRemoteService(retrofitRemoteRestService);
    }
}
