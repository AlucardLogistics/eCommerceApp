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
import com.example.sadic.ecommerceapp.data.database.DbHelper;
import com.example.sadic.ecommerceapp.data.database.model.CartProduct;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecivleViewOrderApapter extends RecyclerView.Adapter<RecivleViewOrderApapter.OrderViewHolder> {

    Context context;
    List<CartProduct> cartProductList;
    DbHelper dbHelper;
    int totalPrice;

    public RecivleViewOrderApapter(Context context, List<CartProduct> cartProductList) {
        this.context = context;
        this.cartProductList = cartProductList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view_order, parent, false);

        return new OrderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {

        final CartProduct cartProduct = cartProductList.get(position);

        holder.tvOrderName.setText("Name:" + cartProduct.getpName());
        holder.tvOrderPrice.setText("Price: " + cartProduct.getpPrice() + " $");

        Picasso.get().load(cartProduct.getpThumbPath())
                .placeholder(R.drawable.placeholder)
                .into(holder.ivOrderImage);

    }

    @Override
    public int getItemCount() {
        return cartProductList.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder{

        TextView tvOrderName, tvOrderPrice;
        ImageView ivOrderImage;

        public OrderViewHolder(View itemView) {
            super(itemView);

            tvOrderName = itemView.findViewById(R.id.tvOrderName);
            tvOrderPrice = itemView.findViewById(R.id.tvOrderPrice);
            ivOrderImage = itemView.findViewById(R.id.ivOrderImage);
        }
    }
}
