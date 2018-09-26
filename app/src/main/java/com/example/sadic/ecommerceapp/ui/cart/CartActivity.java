package com.example.sadic.ecommerceapp.ui.cart;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.sadic.ecommerceapp.R;
import com.example.sadic.ecommerceapp.adapters.RecyclerViewCartAdapter;
import com.example.sadic.ecommerceapp.data.database.model.CartProduct;
import com.example.sadic.ecommerceapp.ui.mainfeed.MainActivity;
import com.example.sadic.ecommerceapp.utils.SharedPref;

import java.util.List;

public class CartActivity extends AppCompatActivity implements IViewCart {
    private static final String TAG = "CartActivity";

    IPresenterCart presenterCart;
    RecyclerView rvCart;
    RecyclerViewCartAdapter adapter;
    ProgressDialog pd;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        init();

        presenterCart = new PresenterCart(this);
        presenterCart.getActivityData();
    }

    void init() {
        rvCart = findViewById(R.id.rvCartView);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvCart.setLayoutManager(layoutManager);


        pd = new ProgressDialog(this);
        pd.setTitle("My Progress Dialog");
        pd.setMessage("Fetching data from the database!");
        pd.setCancelable(false);
        showDialog();
    }


    public void eventHandler(View view) {
        presenterCart.onButtonClicked(view);
    }

    @Override
    public void showCartList(List<CartProduct> cartProductList) {
        adapter = new RecyclerViewCartAdapter(this, cartProductList);
        Log.d(TAG, "showCartList: adapter " + adapter.getItemCount());
        rvCart.setAdapter(adapter);
        updateCart(cartProductList);
        dismissDialog();
    }

    private void updateCart(List<CartProduct> cartProductList) {
        Log.d(TAG, "updateCart: started");
        SharedPref.init(context);
        SharedPref.write(SharedPref.CART_ITEMS, cartProductList.size());
        Log.d(TAG, "showCartList: CARTSIZE_SharePref: "
                + SharedPref.read(SharedPref.CART_ITEMS, 0));
    }

    private void showDialog() {
        if(!pd.isShowing()) {
            pd.show();
        }
    }

    private void dismissDialog() {
        if(pd.isShowing()) {
            pd.dismiss();
        }
    }
}
