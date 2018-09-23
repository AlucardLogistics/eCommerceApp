package com.example.sadic.ecommerceapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sadic.ecommerceapp.R;

import com.example.sadic.ecommerceapp.data.network.model.Product;
import com.example.sadic.ecommerceapp.ui.product_detail.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewProductAdapter extends RecyclerView.Adapter<RecyclerViewProductAdapter.ProductViewHolder> {

    Context context;
    List<Product> productList;

    public RecyclerViewProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    public RecyclerViewProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view_product, parent, false);

        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        final String pId = product.getpId();
        final String pName = product.getpName();
        final String pQuantity = product.getpQuantity();
        final String pPrice = product.getpPrice();
        final String pDescription = product.getpDescription();
        final String pImgUrl = product.getpThumbUrl();

        holder.tvId.setText("Id: " + product.getpId());
        holder.tvName.setText(product.getpName());
        holder.tvQuantity.setText("Quantity: " + product.getpQuantity());
        holder.tvPrice.setText("Price: " + product.getpPrice());
        holder.tvDescription.setText(product.getpDescription());

        Picasso.get()
                .load(product.getpThumbUrl())
                .placeholder(R.drawable.placeholder)
                .into(holder.ivThumbImage);

        holder.ivThumbImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putString("pId", pId);
                b.putString("pName", pName);
                b.putString("pQuantity", pQuantity);
                b.putString("pPrice", pPrice);
                b.putString("pDescription", pDescription);
                b.putString("pImgUrl", pImgUrl);
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtras(b);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        TextView tvId, tvName, tvQuantity, tvPrice, tvDescription;
        ImageView ivThumbImage;


        public ProductViewHolder(View itemView) {
            super(itemView);
            this.tvId = itemView.findViewById(R.id.tvIdProd);
            this.tvName = itemView.findViewById(R.id.tvNameProd);
            this.tvQuantity = itemView.findViewById(R.id.tvQuantityProd);
            this.tvPrice = itemView.findViewById(R.id.tvPriceProd);
            this.tvDescription = itemView.findViewById(R.id.tvDescriptionProd);
            this.ivThumbImage = itemView.findViewById(R.id.ivThumbProd);
        }
    }
}
