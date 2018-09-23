package com.example.sadic.ecommerceapp.ui.cart;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.sadic.ecommerceapp.R;
import com.example.sadic.ecommerceapp.adapters.RecyclerViewCartAdapter;
import com.example.sadic.ecommerceapp.data.database.model.CartProduct;

import java.util.List;

public class CartActivity extends AppCompatActivity implements IViewCart {
    private static final String TAG = "CartActivity";

    IPresenterCart presenterCart;
    RecyclerView rvCart;
    RecyclerViewCartAdapter adapter;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        rvCart = findViewById(R.id.rvCartView);

//        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
//        rvCart.setLayoutManager(manager);
//        rvCart.setItemAnimator(new DefaultItemAnimator());

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvCart.setLayoutManager(layoutManager);


        pd = new ProgressDialog(this);
        pd.setTitle("My Progress Dialog");
        pd.setMessage("Fetching data from the database!");
        pd.setCancelable(false);
        //showDialog();

        presenterCart = new PresenterCart(this);
        presenterCart.getActivityData();
    }

    public void eventHandler(View view) {

    }

    @Override
    public void showCartList(List<CartProduct> cartProductList) {
        adapter = new RecyclerViewCartAdapter(this, cartProductList);
        Log.d(TAG, "showCartList: cartProductList: " + cartProductList.toString());
        Log.d(TAG, "showCartList: adapter " + adapter.getItemCount());
        rvCart.setAdapter(adapter);
        //dismissDialog();
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
