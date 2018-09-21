package com.example.sadic.ecommerceapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sadic.ecommerceapp.R;
import com.example.sadic.ecommerceapp.data.network.model.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewCategory extends RecyclerView.Adapter<RecyclerViewCategory.CategoryViewHolder> {

    Context context;
    List<Category> categoryList;

    public RecyclerViewCategory(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_item_view, parent, false);


        return new CategoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

        Category category = categoryList.get(position);

        holder.tvIdCat.setText(category.getcId());
        holder.tvNameCat.setText(category.getcName());
        holder.tvDescriptionCat.setText(category.getcDescription());

        Picasso.get()
                .load(category.getcImage())
                .placeholder(R.drawable.placeholder)
                .into(holder.ivThumbCat);

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView tvIdCat, tvNameCat, tvDescriptionCat;
        ImageView ivThumbCat;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            tvIdCat = itemView.findViewById(R.id.tvIdCat);
            tvNameCat = itemView.findViewById(R.id.tvNameCat);
            tvDescriptionCat = itemView.findViewById(R.id.tvDescriptionCat);
            ivThumbCat = itemView.findViewById(R.id.ivThumbCat);

        }
    }
}
