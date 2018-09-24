package com.example.sadic.ecommerceapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sadic.ecommerceapp.R;
import com.example.sadic.ecommerceapp.data.database.DbHelper;
import com.example.sadic.ecommerceapp.data.database.model.CartProduct;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewCartAdapter extends RecyclerView.Adapter<RecyclerViewCartAdapter.CartViewHolder> {
    private static final String TAG = "RecyclerViewCartAdapter";

    Context context;
    List<CartProduct> cartProductList;
    DbHelper dbHelper;

    public RecyclerViewCartAdapter(Context context, List<CartProduct> cartProductList) {
        this.context = context;
        this.cartProductList = cartProductList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: started");

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view_cart, parent, false);

        return new CartViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: started");
        final CartProduct cartProduct = cartProductList.get(position);

        if(cartProduct.getIsCart() == 1) {
            holder.tvCartName.setText("Name:" + cartProduct.getpName());
            holder.tvCartPrice.setText("Price: " + cartProduct.getpPrice() + " $");
            holder.tvCartDescription.setText("About: " + cartProduct.getpDescription());
            Picasso.get().load(cartProduct.getpThumbPath())
                    .placeholder(R.drawable.placeholder)
                    .into(holder.ivCartImage);

            holder.tvCartRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dbHelper = new DbHelper(context);
                    int pos = holder.getAdapterPosition();
                    dbHelper.deleteCartOrWishRow(cartProduct.getpName(), 1, 0);
                    cartProductList.remove(pos);
                    notifyItemRemoved(pos);
                    notifyItemRangeChanged(pos, cartProductList.size());
                    notifyDataSetChanged();
                }
            });

        } else if(cartProduct.getIsCart() == 0) {
            holder.itemView.setVisibility(View.GONE);
            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
        }




    }

    @Override
    public int getItemCount() {
        return cartProductList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{

        TextView tvCartName, tvCartPrice, tvCartDescription, tvCartRemove;
        ImageView ivCartImage;

        public CartViewHolder(View itemView) {
            super(itemView);

            tvCartName = itemView.findViewById(R.id.tvCartName);
            tvCartPrice = itemView.findViewById(R.id.tvCartPrice);
            tvCartDescription = itemView.findViewById(R.id.tvCartDescription);
            tvCartRemove = itemView.findViewById(R.id.tvCartRemove);
            ivCartImage = itemView.findViewById(R.id.ivCartImage);
        }
    }
}
