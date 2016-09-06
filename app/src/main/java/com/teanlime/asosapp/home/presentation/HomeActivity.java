package com.teanlime.asosapp.home.presentation;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.teanlime.asosapp.R;
import com.teanlime.asosapp.application.presentation.AsosApplication;
import com.teanlime.asosapp.base.model.ActivityComponentFlyweight;
import com.teanlime.asosapp.base.presentation.AsosActivity;
import com.teanlime.asosapp.category.presentation.CategoryFragment;
import com.teanlime.asosapp.home.di.HomeActivityComponent;
import com.teanlime.asosapp.home.model.HomeActivityComponentFactory;
import com.teanlime.domain.categories.model.response.Category;
import com.teanlime.domain.categories.presentation.CategoriesView;
import com.teanlime.domain.categories.presentation.HomePresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static com.teanlime.asosapp.R.id.drawer;

public class HomeActivity extends AsosActivity<HomeActivityComponent> implements CategoriesView,
        NavigationView.OnNavigationItemSelectedListener {

    private static final String SELECTED_CATEGORY_GROUP = "selected_category_group";
    private static final String CATEGORY_FRAGMENT_TAG = "category_fragment_tag";
    private static final String TAG = "HomeActivity";

    private static final int DRAWER_GRAVITY = GravityCompat.START;

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
    HomePresenter presenter;

    private String selectedCategoryGroup;

    private ToggleButton womenMenuButton;
    private ToggleButton menMenuButton;

    @NonNull
    public static Intent createStartIntent(@NonNull Context context) {
        return new Intent(context, HomeActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.attachView(this);
        presenter.onCreate(savedInstanceState == null ? null : savedInstanceState.getString(SELECTED_CATEGORY_GROUP));
    }

    @Override
    public void setSelectedCategoriesGroup(String selectedCategoryGroup) {
        this.selectedCategoryGroup = selectedCategoryGroup;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SELECTED_CATEGORY_GROUP, selectedCategoryGroup);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @NonNull
    @Override
    public HomeActivityComponent createActivityComponent() {
        final HomeActivityComponent homeActivityComponent = new ActivityComponentFlyweight<>(new
                HomeActivityComponentFactory()).create(this);
        homeActivityComponent.inject(this);
        return homeActivityComponent;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_home;
    }

    @Override
    public void setupViews() {
        setupErrorScreen();
        setupToolbar();
        setupNavigationDrawer();
    }

    private void setupErrorScreen() {
        Glide.with(this).load(Uri.parse("file:///android_asset/logo-splash.gif"))
                .asBitmap()
                .sizeMultiplier(0.37f)
                .into(asosLogo);
    }

    private void setupToolbar() {
        toolbar.setTitle(R.string.asos);
        setSupportActionBar(toolbar);
    }

    private void setupNavigationDrawer() {
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);

        setupNavigationDrawerHeader();

        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * Doesn't work in Butterknife at the moment, needs to be inflated manually
     */
    private void setupNavigationDrawerHeader() {
        final View headerView = navigationView.inflateHeaderView(R.layout.navigation_drawer_header);
        womenMenuButton = (ToggleButton) headerView.findViewById(R.id.women_button);
        menMenuButton = (ToggleButton) headerView.findViewById(R.id.men_button);

        womenMenuButton.setOnTouchListener((v, event) ->
                presenter.onWomenMenuButtonTouchEvent(event.getAction() == MotionEvent.ACTION_UP));

        menMenuButton.setOnTouchListener((v, event) ->
                presenter.onMenMenuButtonTouchEvent(event.getAction() == MotionEvent.ACTION_UP));
    }

    @Override
    public void selectWomenCategoryGroup() {
        new Handler().post(() -> {
            womenMenuButton.setPressed(true);
            womenMenuButton.setChecked(true);
        });
    }

    @Override
    public boolean isLollipop() {
        return AsosApplication.get(this).isLollipop();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void selectWomenCategoryGroupLollipopExtra() {
        new Handler().post(() -> womenMenuButton.setElevation(15));
    }

    @Override
    public void deselectMenCategoryGroup() {
        new Handler().post(() -> {
            menMenuButton.setPressed(false);
            menMenuButton.setChecked(false);
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void selectMenCategoryGroupLollipopExtra() {
        new Handler().post(() -> menMenuButton.setElevation(15));
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void deselectMenCategoryGroupLollipopExtra() {
        new Handler().post(() -> menMenuButton.setElevation(0));
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void deselectWomenCategoryGroupLollipopExtra() {
        new Handler().post(() -> womenMenuButton.setElevation(0));
    }

    @Override
    public void selectMenCategoryGroup() {
        new Handler().post(() -> {
            menMenuButton.setPressed(true);
            menMenuButton.setChecked(true);
        });
    }

    @Override
    public void deselectWomenCategoryGroup() {
        new Handler().post(() -> {
            womenMenuButton.setPressed(false);
            womenMenuButton.setChecked(false);
        });
    }

    @Override
    public void displayLoading() {
        loadingScreen.setVisibility(View.VISIBLE);
        homeScreen.setVisibility(View.GONE);
    }

    @Override
    public void displayContent() {
        loadingScreen.setVisibility(View.GONE);
        homeScreen.setVisibility(View.VISIBLE);
    }

    @Override
    public void setNavigationDrawerSubmenuCategories(String categoriesGroup, List<Category> categoryList) {
        Log.d(TAG, "setNavigationDrawerSubmenuCategories: " + categoriesGroup + " " + categoryList.toString());

        navigationView.getMenu().clear();

        final SubMenu subMenu = navigationView.getMenu().addSubMenu(categoriesGroup);
        for (int i = 0; i < categoryList.size(); i++) {
            subMenu.add(Menu.NONE, i, Menu.NONE, categoryList.get(i).getName());
        }
    }

    @Override
    public void displayCategoriesError(String exception) {
        Log.e(TAG, "displayCategoriesError: " + exception);

        // TODO create error screen
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                openNavigationDrawer();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void openNavigationDrawer() {
        drawerLayout.openDrawer(DRAWER_GRAVITY, true);
    }

    @Override
    public boolean isNavigationDrawerOpen() {
        return drawerLayout.isDrawerOpen(DRAWER_GRAVITY);
    }

    @Override
    public void onBackPressed() {
        presenter.onBackPressed();
    }

    @Override
    public void processOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_settings, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        presenter.onNavigationItemSelected(selectedCategoryGroup, item.getItemId());
        return true;
    }

    @Override
    public void startCategoryFragment(String categoryId) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, CategoryFragment.newInstance(categoryId), CATEGORY_FRAGMENT_TAG)
                .commit();
    }

    @Override
    public void closeNavigationDrawer() {
        drawerLayout.closeDrawer(DRAWER_GRAVITY, true);
    }
}