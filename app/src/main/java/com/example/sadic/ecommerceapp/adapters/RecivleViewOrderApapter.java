package com.example.sadic.ecommerceapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sadic.ecommerceapp.R;
import com.example.sadic.ecommerceapp.data.database.DbHelper;
import com.example.sadic.ecommerceapp.data.database.model.CartProduct;
import com.example.sadic.ecommerceapp.utils.SharedPref;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecivleViewOrderApapter extends RecyclerView.Adapter<RecivleViewOrderApapter.OrderViewHolder> {
    private static final String TAG = "RecivleViewOrderApapter";

    Context context;
    List<CartProduct> cartProductList;
    DbHelper dbHelper;

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
    public void onBindViewHolder(@NonNull final OrderViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: started");

        final CartProduct cartProduct = cartProductList.get(position);

        holder.tvOrderName.setText("Name: " + cartProduct.getpName());
        holder.tvOrderPrice.setText("Price: " + cartProduct.getpPrice() + " $");

        Picasso.get().load(cartProduct.getpThumbPath())
                .placeholder(R.drawable.placeholder)
                .into(holder.ivOrderImage);

        holder.btOrderRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper = new DbHelper(context);
                int pos = holder.getAdapterPosition();
                int posInTable = Integer.parseInt(cartProduct.getId());
                Log.d(TAG, "onClick: posInTable: " + posInTable);
                dbHelper.deleteCartOrWishRowWithId(posInTable, cartProduct.getpId(), 1, 0);
                cartProductList.remove(pos);
                notifyItemRemoved(pos);
                notifyItemRangeChanged(pos, cartProductList.size());
                notifyDataSetChanged();
                //update cart badge
                updateCartCount();
            }
        });

        holder.ivOrderWishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = cartProduct.getId();
                String pID = cartProduct.getpId();
                String pName = cartProduct.getpName();
                String pQuantity = cartProduct.getpQuantity();
                String pPrice = cartProduct.getpPrice();
                String pDescription = cartProduct.getpDescription();
                String pThumbPath = cartProduct.getpThumbPath();

                CartProduct cartAddWish = new CartProduct(pID, pName, pQuantity, pPrice,
                        pDescription, pThumbPath, 0, 1);
                Log.d(TAG, "onClick: cartAddWish: " + cartAddWish.toString());
                Log.d(TAG, "onClick: PID: " + pID);
                dbHelper = new DbHelper(context);
                dbHelper.createWishRow(cartAddWish, pID, 1);

            }
        });

    }

    public void updateCartCount() {
        SharedPref.init(context);
        int cartCount = SharedPref.read(SharedPref.CART_ITEMS, 0);
        cartCount = cartCount - 1;
        SharedPref.write(SharedPref.CART_ITEMS, cartCount);
    }

    @Override
    public int getItemCount() {
        return cartProductList.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder{

        TextView tvOrderName, tvOrderPrice;
        ImageView ivOrderImage, ivOrderWishList;
        Button btOrderRemove;

        public OrderViewHolder(View itemView) {
            super(itemView);

            tvOrderName = itemView.findViewById(R.id.tvOrderName);
            tvOrderPrice = itemView.findViewById(R.id.tvOrderPrice);
            btOrderRemove = itemView.findViewById(R.id.btOrderRemove);
            ivOrderWishList = itemView.findViewById(R.id.ivOrderAddWishList);
            ivOrderImage = itemView.findViewById(R.id.ivOrderImage);
        }
    }
}
