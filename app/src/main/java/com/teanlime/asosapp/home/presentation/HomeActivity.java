package com.teanlime.asosapp.home.presentation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.teanlime.asosapp.R;
import com.teanlime.asosapp.base.action.AndroidRxSchedulerFactory;
import com.teanlime.asosapp.base.presentation.AsosActivity;
import com.teanlime.data.api.mapper.ListMapper;
import com.teanlime.data.api.mapper.StringUseCaseExceptionMapper;
import com.teanlime.data.api.remote.DecoratedRetrofitRemoteService;
import com.teanlime.data.api.remote.rest.retrofit.RetrofitRemoteRestServiceFactory;
import com.teanlime.data.api.usecase.CallbackSubscriber;
import com.teanlime.data.api.usecase.RxUseCaseSubscription;
import com.teanlime.data.categories.model.repository.CategoriesRepositoryMediator;
import com.teanlime.data.categories.model.repository.local.CategoriesInMemoryLocalRepository;
import com.teanlime.data.categories.model.repository.remote.CategoriesRemoteRepository;
import com.teanlime.data.categories.model.request.mapper.CategoriesGroupMapper;
import com.teanlime.data.categories.model.response.CategoryModel;
import com.teanlime.data.categories.model.response.mapper.CategoriesMapper;
import com.teanlime.data.categories.model.response.mapper.CategoryMapper;
import com.teanlime.data.categories.usecase.GetCategoriesRepositoryUseCase;
import com.teanlime.domain.api.usecase.EmptyUseCaseCallback;
import com.teanlime.domain.api.util.StringUtils;
import com.teanlime.domain.categories.model.response.Categories;
import com.teanlime.domain.categories.model.response.Category;
import com.teanlime.domain.categories.presentation.CategoriesPresenter;
import com.teanlime.domain.categories.presentation.CategoriesView;
import com.teanlime.domain.categories.usecase.GetCategoriesUseCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//import butterknife.BindView;

public class HomeActivity extends AsosActivity implements CategoriesView {

    //@BindView(R.id.splash_logo)
    ImageView asosLogo;

    @NonNull
    public static Intent createStartIntent(@NonNull Context context) {
        return new Intent(context, HomeActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO Use via Butterknife
        asosLogo = (ImageView) findViewById(R.id.splash_logo);
        Glide.with(this).load(Uri.parse("file:///android_asset/logo-splash.gif"))
                .asBitmap()
                .sizeMultiplier(0.37f)
                .into(asosLogo);

        final CategoriesPresenter presenter = provideCategoriesPresenter();
        presenter.attachView(this);
    }

    @NonNull
    private CategoriesPresenter provideCategoriesPresenter() {
        return new CategoriesPresenter(provideGetCategoriesUseCase());
    }

    private GetCategoriesUseCase provideGetCategoriesUseCase() {
        return new GetCategoriesRepositoryUseCase(
                new RxUseCaseSubscription<>(provideCallbackSubscriber(), new AndroidRxSchedulerFactory()),
                provideCategoriesRepositoryMediator());
    }

    @NonNull
    private CallbackSubscriber<Categories, String> provideCallbackSubscriber() {
        return new CallbackSubscriber<>(new EmptyUseCaseCallback<>(), new StringUseCaseExceptionMapper());
    }

    private CategoriesRepositoryMediator provideCategoriesRepositoryMediator() {
        return new CategoriesRepositoryMediator(
                new CategoriesInMemoryLocalRepository(),
                provideRemoteCategoriesRepository());
    }

    @NonNull
    private CategoriesRemoteRepository provideRemoteCategoriesRepository() {
        return new CategoriesRemoteRepository(
                new CategoriesGroupMapper(),
                provideCategoriesMapper(),
                StringUtils.EMPTY,
                provideDecoratedRetrofitRemoteService());
    }

    @NonNull
    private CategoriesMapper provideCategoriesMapper() {
        return new CategoriesMapper(provideCategoryListMapper(), provideFallbackCategoryList());
    }

    @NonNull
    private DecoratedRetrofitRemoteService provideDecoratedRetrofitRemoteService() {
        return new DecoratedRetrofitRemoteService(new RetrofitRemoteRestServiceFactory().create());
    }

    @NonNull
    private ListMapper<CategoryModel, Category> provideCategoryListMapper() {
        return new ListMapper<>(new CategoryMapper());
    }

    private List<Category> provideFallbackCategoryList() {
        return Collections.unmodifiableList(new ArrayList<>());
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public void displayCategories(Categories model) {
        Log.d("ANNALOG", "HomeActivity displayCategories: " + model.toString());
    }

    @Override
    public void displayCategoriesError(String exception) {
        Log.e("ANNALOG", "HomeActivity displayCategoriesError: " + exception);
    }
}