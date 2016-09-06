package com.teanlime.asosapp.categorylisting.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.teanlime.asosapp.R;
import com.teanlime.asosapp.base.model.FragmentComponentFlyweight;
import com.teanlime.asosapp.base.presentation.AsosFragment;
import com.teanlime.asosapp.categorylisting.di.CategoryListingFragmentComponent;
import com.teanlime.asosapp.categorylisting.model.CategoryListingFragmentComponentFactory;

import butterknife.BindView;

public class CategoryListingFragment extends AsosFragment<CategoryListingFragmentComponent> {

    private static final String CATEGORY_ID_BUNDLE = "category_id_bundle";

    @BindView(R.id.fragment_category_text)
    TextView text;

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
                CategoryListingFragmentComponentFactory()).create(this);
        categoryListingFragmentComponent.inject(this);
        return categoryListingFragmentComponent;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        text.setText(getArguments().getString(CATEGORY_ID_BUNDLE));
    }
}
