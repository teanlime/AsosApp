package com.teanlime.asosapp.category.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.teanlime.asosapp.R;
import com.teanlime.asosapp.base.model.FragmentComponentFlyweight;
import com.teanlime.asosapp.base.presentation.AsosFragment;
import com.teanlime.asosapp.category.di.CategoryFragmentComponent;
import com.teanlime.asosapp.category.model.CategoryFragmentComponentFactory;

import butterknife.BindView;

public class CategoryFragment extends AsosFragment<CategoryFragmentComponent> {

    private static final String CATEGORY_ID_BUNDLE = "category_id_bundle";

    @BindView(R.id.fragment_category_text)
    TextView text;

    public static CategoryFragment newInstance(String categoryId) {
        final Bundle bundle = new Bundle();
        bundle.putString(CATEGORY_ID_BUNDLE, categoryId);

        final CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_category;
    }

    @NonNull
    @Override
    protected CategoryFragmentComponent createFragmentComponent() {
        final CategoryFragmentComponent categoryFragmentComponent = new FragmentComponentFlyweight<>(new
                CategoryFragmentComponentFactory()).create(this);
        categoryFragmentComponent.inject(this);
        return categoryFragmentComponent;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        text.setText(getArguments().getString(CATEGORY_ID_BUNDLE));
    }
}
