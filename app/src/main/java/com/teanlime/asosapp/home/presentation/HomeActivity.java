package com.teanlime.asosapp.home.presentation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.teanlime.asosapp.R;
import com.teanlime.asosapp.base.presentation.AsosActivity;
import com.teanlime.asosapp.home.di.HomeActivityComponent;
import com.teanlime.asosapp.home.model.ActivityComponentFlyweight;
import com.teanlime.asosapp.home.model.HomeActivityComponentFactory;
import com.teanlime.domain.categories.model.response.Categories;
import com.teanlime.domain.categories.presentation.CategoriesPresenter;
import com.teanlime.domain.categories.presentation.CategoriesView;

import javax.inject.Inject;

import butterknife.BindView;

public class HomeActivity extends AsosActivity implements CategoriesView {

    @BindView(R.id.splash_logo)
    ImageView asosLogo;

    @BindView(R.id.screen_loading)
    ViewGroup loadingScreen;

    @Inject
    CategoriesPresenter presenter;

    private HomeActivityComponent homeActivityComponent;

    @NonNull
    public static Intent createStartIntent(@NonNull Context context) {
        return new Intent(context, HomeActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Glide.with(this).load(Uri.parse("file:///android_asset/logo-splash.gif"))
                .asBitmap()
                .sizeMultiplier(0.37f)
                .into(asosLogo);

        presenter.attachView(this);
    }

    @NonNull
    @Override
    public HomeActivityComponent getActivityComponent() {
        if (homeActivityComponent == null) {
            homeActivityComponent = new ActivityComponentFlyweight<>(new HomeActivityComponentFactory()).create(this);
            homeActivityComponent.inject(this);
        }
        return homeActivityComponent;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_home;
    }

    @Override
    public void displayLoading() {
        loadingScreen.setVisibility(View.VISIBLE);
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