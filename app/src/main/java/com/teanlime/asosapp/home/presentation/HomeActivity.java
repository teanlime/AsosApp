package com.teanlime.asosapp.home.presentation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
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
import com.teanlime.domain.categories.model.response.Category;
import com.teanlime.domain.categories.presentation.CategoriesPresenter;
import com.teanlime.domain.categories.presentation.CategoriesView;

import javax.inject.Inject;

import butterknife.BindView;

import static com.teanlime.asosapp.R.id.drawer;

public class HomeActivity extends AsosActivity implements CategoriesView,
        NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.splash_logo)
    ImageView asosLogo;

    @BindView(R.id.screen_loading)
    ViewGroup loadingScreen;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.content)
    ViewGroup content;

    @BindView(R.id.screen_home)
    View homeScreen;

    @BindView(drawer)
    DrawerLayout drawerLayout;

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

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
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
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
    public void initViews() {
        Glide.with(this).load(Uri.parse("file:///android_asset/logo-splash.gif"))
                .asBitmap()
                .sizeMultiplier(0.37f)
                .into(asosLogo);

        setupToolbar();
        setupNavigationDrawer();
    }

    private void setupToolbar() {
        toolbar.setTitle(R.string.asos);
        setSupportActionBar(toolbar);
    }

    private void setupNavigationDrawer() {
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);

        final View headerView = navigationView.inflateHeaderView(R.layout.navigation_drawer_header);
        final ImageView navigationDrawerImage = (ImageView) headerView.findViewById(R.id.navigation_drawer_header_image);

        Glide.with(this).load(Uri.parse("file:///android_asset/logo-splash.gif"))
                .asBitmap()
                .into(navigationDrawerImage);

        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void displayLoading() {
        loadingScreen.setVisibility(View.VISIBLE);
    }

    @Override
    public void displayCategories(Categories model) {
        Log.d("ANNALOG", "HomeActivity displayCategories: " + model.toString());

        final Menu menu = navigationView.getMenu();
        final SubMenu subMenu = menu.addSubMenu(model.getDescription());
        for (Category category : model.getListing()) {
            subMenu.add(category.getName());
        }

        loadingScreen.setVisibility(View.GONE);
        homeScreen.setVisibility(View.VISIBLE);

        drawerLayout.openDrawer(GravityCompat.START, true);
    }

    @Override
    public void displayCategoriesError(String exception) {
        Log.e("ANNALOG", "HomeActivity displayCategoriesError: " + exception);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START, true);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START, true);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_settings, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        drawerLayout.closeDrawer(GravityCompat.START, true);
        return true;
    }
}