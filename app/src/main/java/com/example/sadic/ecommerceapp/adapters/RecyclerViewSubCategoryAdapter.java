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
import com.example.sadic.ecommerceapp.data.network.model.SubCategory;
import com.example.sadic.ecommerceapp.ui.subcategory.IPresenterSubCategory;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewSubCategoryAdapter extends RecyclerView.Adapter<RecyclerViewSubCategoryAdapter.SubCategoryViewHolder> {

    Context context;
    List<SubCategory> subCategoryList;
    IPresenterSubCategory listener;

    public RecyclerViewSubCategoryAdapter(Context context, List<SubCategory> subCategoryList) {
        this.context = context;
        this.subCategoryList = subCategoryList;
    }

    public RecyclerViewSubCategoryAdapter(List<SubCategory> subCategoryList, IPresenterSubCategory listener) {
        this.listener = listener;
        this.subCategoryList = subCategoryList;
    }

    @NonNull
    @Override
    public SubCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view_sub_category, parent, false);
        final SubCategoryViewHolder svh = new SubCategoryViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, svh.getPosition());
            }
        });

        return svh;
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoryViewHolder holder, int position) {
        SubCategory subCategory = subCategoryList.get(position);

        holder.tvIdSubCat.setText(subCategory.getScId());
        holder.tvNameSubCat.setText(subCategory.getScName());
        holder.tvDescriptionSubCat.setText(subCategory.getScDescription());

        Picasso.get().load(subCategory.getScImage())
                .placeholder(R.drawable.placeholder)
                .into(holder.ivThumbSubCat);

    }

    @Override
    public int getItemCount() {
        return subCategoryList.size();
    }

    public class SubCategoryViewHolder extends RecyclerView.ViewHolder {

        TextView tvIdSubCat, tvNameSubCat, tvDescriptionSubCat;
        ImageView ivThumbSubCat;

        public SubCategoryViewHolder(View itemView) {
            super(itemView);
            tvIdSubCat = itemView.findViewById(R.id.tvIdSubCat);
            tvNameSubCat = itemView.findViewById(R.id.tvNameSubCat);
            tvDescriptionSubCat = itemView.findViewById(R.id.tvDescriptionSubCat);
            ivThumbSubCat = itemView.findViewById(R.id.ivThumbSubCat);

        }
    }
}
