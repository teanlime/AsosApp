package com.teanlime.asosapp.categorylisting.presentation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.teanlime.asosapp.R;
import com.teanlime.domain.categorylisting.model.response.Listings;

import java.util.List;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryGridItemViewHolder> {

    private List<Listings> listings;
    private Context context;

    public CategoryRecyclerViewAdapter(Context context, List<Listings> listings) {
        this.context = context;
        this.listings = listings;
    }

    @Override
    public CategoryGridItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_category_grid_item, parent,
                false);
        return new CategoryGridItemViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(CategoryGridItemViewHolder holder, int position) {
        final Listings listings = this.listings.get(position);

        holder.categoryGridItemPrice.setText(listings.getCurrentPrice());

        List<String> productImageUrl = listings.getProductImageUrl();
        if (productImageUrl != null && productImageUrl.size() > 0) {

            Glide.with(context)
                    .load(productImageUrl.get(0))
                    .fitCenter()
                    .into(holder.categoryGridItemImage);
        }
    }

    @Override
    public int getItemCount() {
        return listings.size();
    }
}
