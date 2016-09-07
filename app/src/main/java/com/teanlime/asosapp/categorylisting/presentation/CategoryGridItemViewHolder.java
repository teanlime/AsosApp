package com.teanlime.asosapp.categorylisting.presentation;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.teanlime.asosapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryGridItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.category_listing_grid_item_price)
    TextView categoryGridItemPrice;

    @BindView(R.id.category_listing_grid_item_image)
    ImageView categoryGridItemImage;

    public CategoryGridItemViewHolder(View categoryGridItem) {
        super(categoryGridItem);
        ButterKnife.bind(this, categoryGridItem);
        categoryGridItem.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Clicked on: " + getLayoutPosition(), Toast.LENGTH_SHORT).show();
    }


}