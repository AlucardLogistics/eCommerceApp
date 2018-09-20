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
import com.example.sadic.ecommerceapp.data.database.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewProduct extends RecyclerView.Adapter<RecyclerViewProduct.ProductViewHolder> {

    Context context;
    List<Product> productList;

    public RecyclerViewProduct(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item_view, parent, false);

        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.tvId.setText("Id: " + product.getpId());
        holder.tvName.setText(product.getpName());
        holder.tvQuantity.setText("Quantity: " + product.getpQuantity());
        holder.tvPrice.setText("Price: " + product.getpPrice());
        holder.tvDescription.setText(product.getpDescription());

        Picasso.get()
                .load(product.getpThumbUrl())
                .placeholder(R.drawable.placeholder)
                .into(holder.ivThumbImage);
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
            this.tvId = itemView.findViewById(R.id.tvId);
            this.tvName = itemView.findViewById(R.id.tvName);
            this.tvQuantity = itemView.findViewById(R.id.tvQuantity);
            this.tvPrice = itemView.findViewById(R.id.tvPrice);
            this.tvDescription = itemView.findViewById(R.id.tvDescription);
            this.ivThumbImage = itemView.findViewById(R.id.ivThumb);
        }
    }
}
