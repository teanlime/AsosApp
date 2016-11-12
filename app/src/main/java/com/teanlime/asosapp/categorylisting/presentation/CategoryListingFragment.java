package com.teanlime.asosapp.categorylisting.presentation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.teanlime.asosapp.R;
import com.teanlime.asosapp.base.model.FragmentComponentFlyweight;
import com.teanlime.asosapp.base.presentation.AsosActivity;
import com.teanlime.asosapp.base.presentation.AsosFragment;
import com.teanlime.asosapp.categorylisting.di.CategoryListingFragmentComponent;
import com.teanlime.asosapp.categorylisting.model.CategoryListingFragmentComponentFactory;
import com.teanlime.asosapp.productdetails.presentation.ProductDetailsFragment;
import com.teanlime.asosapp.utils.recyclerview.ItemClickSupport;
import com.teanlime.domain.categorylisting.model.response.CategoryListing;
import com.teanlime.domain.categorylisting.model.response.Listings;
import com.teanlime.domain.categorylisting.presentation.CategoryListingPresenter;
import com.teanlime.domain.categorylisting.presentation.CategoryListingView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class CategoryListingFragment extends AsosFragment<CategoryListingFragmentComponent>
        implements CategoryListingView, LoaderManager.LoaderCallbacks<String> {

    private static final String POSITION_DETAILS_FRAGMENT_TAG = "position_details_fragment_tag";
    private static final String CATEGORY_ID_BUNDLE = "category_id_bundle";

    @BindView(R.id.fragment_category_listing_name)
    TextView text;

    @BindView(R.id.fragment_category_listing_grid_item)
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
        return R.layout.fragment_category_listing;
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

        ItemClickSupport.addTo(grid).setOnItemClickListener((recyclerView, position, v) ->
                presenter.onProductClicked(position));

        getLoaderManager().initLoader(100, null, this);
    }

    @Override
    public void navigateToPositionDetails(Long productId) {
        final AsosActivity activity = AsosActivity.get(this);
        if (activity != null) {
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(POSITION_DETAILS_FRAGMENT_TAG)
                    .replace(R.id.content, ProductDetailsFragment.newInstance(productId),
                            POSITION_DETAILS_FRAGMENT_TAG)
                    .commit();
        }
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        Log.d("annalog", "loader create");
        return new MyLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        Log.d("annalog", "loader finished");
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        Log.d("annalog", "loader reset");
    }

    private static final class MyLoader extends Loader<String> {

        /**
         * Stores away the application context associated with context.
         * Since Loaders can be used across multiple activities it's dangerous to
         * store the context directly; always use {@link #getContext()} to retrieve
         * the Loader's Context, don't use the constructor argument directly.
         * The Context returned by {@link #getContext} is safe to use across
         * Activity instances.
         *
         * @param context used to retrieve the application context.
         */
        public MyLoader(Context context) {
            super(context);
        }
    }
}
