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
import com.example.sadic.ecommerceapp.data.database.DbHelper;
import com.example.sadic.ecommerceapp.data.database.model.CartProduct;
import com.example.sadic.ecommerceapp.ui.product_detail.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewWishListAdapter extends RecyclerView.Adapter<RecyclerViewWishListAdapter.WishListViewHolder> {

    Context context;
    List<CartProduct> cartProductList;
    DbHelper dbHelper;

    public RecyclerViewWishListAdapter(Context context, List<CartProduct> cartProductList) {
        this.context = context;
        this.cartProductList = cartProductList;
    }

    @NonNull
    @Override
    public WishListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view_wish_list, parent, false);

        return new WishListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final WishListViewHolder holder, final int position) {
        final CartProduct cartProduct = cartProductList.get(position);

        if(cartProduct.getIsWish() == 1) {

            holder.tvWishName.setText(cartProduct.getpName());
            holder.tvWishPrice.setText("Price: " + cartProduct.getpPrice());
            holder.tvWishDescription.setText(cartProduct.getpDescription());
            holder.tvWishQuantity.setText("Items in Stock: " + cartProduct.getpQuantity());

            Picasso.get().load(cartProduct.getpThumbPath())
                    .placeholder(R.drawable.placeholder)
                    .into(holder.ivWishImage);


            holder.ivWishImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle b = new Bundle();
                    b.putString("pId", cartProduct.getpId());
                    b.putString("pName", cartProduct.getpName());
                    b.putString("pQuantity", cartProduct.getpQuantity());
                    b.putString("pPrice", cartProduct.getpPrice());
                    b.putString("pDescription", cartProduct.getpDescription());
                    b.putString("pImgUrl", cartProduct.getpThumbPath());
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtras(b);
                    context.startActivity(i);
                }
            });

            holder.tvWishClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dbHelper = new DbHelper(context);
                    int pos = holder.getAdapterPosition();
                    dbHelper.deleteCartOrWishRow(cartProduct.getpName(), 0, 1);
                    cartProductList.remove(pos);
                    notifyItemRemoved(pos);
                    notifyItemRangeChanged(pos, cartProductList.size());
                    notifyDataSetChanged();
                }
            });

        } else if(cartProduct.getIsWish() == 0) {
            holder.itemView.setVisibility(View.GONE);
            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
        }
    }

    @Override
    public int getItemCount() {
        return cartProductList.size();
    }

    public class WishListViewHolder extends RecyclerView.ViewHolder {

        TextView tvWishName, tvWishPrice, tvWishDescription, tvWishQuantity, tvWishClose;
        ImageView ivWishImage;

        public WishListViewHolder(View itemView) {
            super(itemView);
            tvWishName = itemView.findViewById(R.id.tvWishName);
            tvWishPrice = itemView.findViewById(R.id.tvWishPrice);
            tvWishDescription = itemView.findViewById(R.id.tvWishDescription);
            tvWishQuantity = itemView.findViewById(R.id.tvWishQuantity);
            tvWishClose = itemView.findViewById(R.id.tvWishClose);
            ivWishImage = itemView.findViewById(R.id.ivWishImage);
        }
    }

}
