package com.example.sadic.ecommerceapp.ui.orders;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.sadic.ecommerceapp.R;
import com.example.sadic.ecommerceapp.adapters.RecivleViewOrderApapter;
import com.example.sadic.ecommerceapp.data.database.model.CartProduct;
import com.example.sadic.ecommerceapp.ui.cart.IPresenterCart;
import com.example.sadic.ecommerceapp.utils.SharedPref;

import java.util.List;

public class OrderActivity extends AppCompatActivity implements IViewOrders{
    private static final String TAG = "OrderActivity";

    IPresenterOrders presenterOrders;
    RecyclerView rvOrder;
    RecivleViewOrderApapter adapter;
    TextView tvTotalPrice, tvUsername, tvUserAddress, tvUserEmail, tvUserMobile;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        init();

        presenterOrders = new PresenterOrders(this);
        presenterOrders.getActivityData();
    }

    private void init() {

        rvOrder = findViewById(R.id.rvOrder);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        tvUsername = findViewById(R.id.tvUser);
        tvUserAddress = findViewById(R.id.tvUserAddress);
        tvUserEmail = findViewById(R.id.tvUserEmail);
        tvUserMobile = findViewById(R.id.tvUserMobile);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvOrder.setLayoutManager(layoutManager);

        SharedPref.init(this);
        String user = SharedPref.read(SharedPref.FIRST_NAME, null)
                + " " + SharedPref.read(SharedPref.LAST_NAME, null);
        tvUsername.setText(user);
        tvUserAddress.setText(SharedPref.read(SharedPref.ADDRESS, null));
        tvUserEmail.setText(SharedPref.read(SharedPref.EMAIL, null));
        tvUserMobile.setText(SharedPref.read(SharedPref.MOBILE, null));


    }

    @Override
    public void showCartList(List<CartProduct> cartProductList) {
        adapter = new RecivleViewOrderApapter(this, cartProductList);
        Log.d(TAG, "showCartList: cartProductList: " + cartProductList.toString());
        Log.d(TAG, "showCartList: adapter " + adapter.getItemCount());
        rvOrder.setAdapter(adapter);
        grandTotal(cartProductList);
    }

    private void grandTotal(List<CartProduct> cartProductList){

        int totalPrice = 0;
        String price;
        for(int i = 0 ; i < cartProductList.size(); i++) {
            price = cartProductList.get(i).getpPrice();
            totalPrice += Integer.parseInt(price);
        }

        tvTotalPrice.setText("Total Price: " + String.valueOf(totalPrice));
    }


    public void eventHandler(View view) {
        Log.d(TAG, "eventHandler: started");
        presenterOrders.onButtonClicked(view);
    }
}
