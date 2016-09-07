package com.teanlime.asosapp.productdetails.presentation;

import android.support.annotation.NonNull;
import android.widget.TextView;

import com.teanlime.asosapp.R;
import com.teanlime.asosapp.base.model.FragmentComponentFlyweight;
import com.teanlime.asosapp.base.presentation.AsosFragment;
import com.teanlime.asosapp.productdetails.di.ProductDetailsFragmentComponent;
import com.teanlime.asosapp.productdetails.model.ProductDetailsFragmentComponentFactory;
import com.teanlime.domain.productdetails.model.response.ProductDetails;
import com.teanlime.domain.productdetails.presentation.ProductDetailsView;

import butterknife.BindView;

public class ProductDetailsFragment extends AsosFragment<ProductDetailsFragmentComponent>
        implements ProductDetailsView {

    private static final String PRODUCT_ID_BUNDLE_EXTRA = "product_id_bundle_extra";

    @BindView(R.id.fragment_product_details_name)
    TextView productName;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_product_details;
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
    }
}
