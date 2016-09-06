package com.teanlime.asosapp.categorylisting.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.teanlime.asosapp.R;
import com.teanlime.asosapp.base.model.FragmentComponentFlyweight;
import com.teanlime.asosapp.base.presentation.AsosFragment;
import com.teanlime.asosapp.categorylisting.di.CategoryListingFragmentComponent;
import com.teanlime.asosapp.categorylisting.model.CategoryListingFragmentComponentFactory;
import com.teanlime.domain.categorylisting.model.response.CategoryListing;
import com.teanlime.domain.categorylisting.model.response.Listings;
import com.teanlime.domain.categorylisting.presentation.CategoryListingPresenter;
import com.teanlime.domain.categorylisting.presentation.CategoryListingView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class CategoryListingFragment extends AsosFragment<CategoryListingFragmentComponent>
        implements CategoryListingView {

    private static final String CATEGORY_ID_BUNDLE = "category_id_bundle";

    @BindView(R.id.fragment_category_name)
    TextView text;

    @BindView(R.id.fragment_category_listing_grid)
    RecyclerView grid;

    @Inject
    CategoryListingPresenter presenter;

    public static CategoryListingFragment newInstance(String categoryId) {
        final Bundle bundle = new Bundle();
        bundle.putString(CATEGORY_ID_BUNDLE, categoryId);

        final CategoryListingFragment fragment = new CategoryListingFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_category;
    }

    @NonNull
    @Override
    protected CategoryListingFragmentComponent createFragmentComponent() {
        final CategoryListingFragmentComponent categoryListingFragmentComponent = new FragmentComponentFlyweight<>(new
                CategoryListingFragmentComponentFactory(getCategoryId())).create(this);
        categoryListingFragmentComponent.inject(this);
        return categoryListingFragmentComponent;
    }

    private String getCategoryId() {
        return getArguments().getString(CATEGORY_ID_BUNDLE);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.attachView(this);
        presenter.onViewCreated();
    }

    @Override
    public void setupViews() {

    }

    @Override
    public void displayLoading() {

    }

    @Override
    public void displayError(String exception) {

    }

    @Override
    public void displayContent(CategoryListing model) {
        final List<Listings> listings = model.getListings();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);

        grid.setHasFixedSize(false);
        grid.setLayoutManager(gridLayoutManager);
        grid.setItemAnimator(new DefaultItemAnimator());

        CategoryRecyclerViewAdapter rcAdapter = new CategoryRecyclerViewAdapter(getActivity(), listings);
        grid.setAdapter(rcAdapter);
    }
}
