package com.teanlime.asosapp.productdetails.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.teanlime.asosapp.R;
import com.teanlime.asosapp.base.model.FragmentComponentFlyweight;
import com.teanlime.asosapp.base.presentation.AsosFragment;
import com.teanlime.asosapp.productdetails.di.ProductDetailsFragmentComponent;
import com.teanlime.asosapp.productdetails.model.ProductDetailsFragmentComponentFactory;
import com.teanlime.domain.productdetails.model.response.ProductDetails;
import com.teanlime.domain.productdetails.presentation.ProductDetailsPresenter;
import com.teanlime.domain.productdetails.presentation.ProductDetailsView;

import javax.inject.Inject;

import butterknife.BindView;

public class ProductDetailsFragment extends AsosFragment<ProductDetailsFragmentComponent>
        implements ProductDetailsView {

    private static final String PRODUCT_ID_BUNDLE_EXTRA = "product_id_bundle_extra";

    @BindView(R.id.fragment_product_details_name)
    TextView productName;

    @BindView(R.id.fragment_product_details_image)
    ImageView productImage;

    @BindView(R.id.fragment_product_details_description)
    TextView productDescription;

    @Inject
    ProductDetailsPresenter presenter;

    public static ProductDetailsFragment newInstance(Long productId) {
        final Bundle bundle = new Bundle();
        bundle.putString(PRODUCT_ID_BUNDLE_EXTRA, productId.toString());

        final ProductDetailsFragment productDetailsFragment = new ProductDetailsFragment();
        productDetailsFragment.setArguments(bundle);
        return productDetailsFragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_product_details;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.attachView(this);
        presenter.onViewCreated();
    }

    @NonNull
    @Override
    protected ProductDetailsFragmentComponent createFragmentComponent() {
        final ProductDetailsFragmentComponent productDetailsFragmentComponent =
                new FragmentComponentFlyweight<>(new ProductDetailsFragmentComponentFactory(getProductId())).create(this);
        productDetailsFragmentComponent.inject(this);
        return productDetailsFragmentComponent;
    }

    public String getProductId() {
        return getArguments().getString(PRODUCT_ID_BUNDLE_EXTRA);
    }

    @Override
    public void setupViews() {

    }

    @Override
    public void displayLoading() {

    }

    @Override
    public void displayError(String exception) {
        productName.setText(exception);
    }

    @Override
    public void displayContent(ProductDetails model) {
        productName.setText(model.getTitle());
        productDescription.setText(model.getDescription());
        Glide.with(this).load(model.getProductImageUrls().get(0)).fitCenter().into(productImage);
    }
}
