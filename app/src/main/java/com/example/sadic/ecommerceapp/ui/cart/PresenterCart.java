package com.example.sadic.ecommerceapp.ui.cart;

import android.util.Log;
import android.view.View;

import com.example.sadic.ecommerceapp.data.DataManager;
import com.example.sadic.ecommerceapp.data.IDataManager;
import com.example.sadic.ecommerceapp.data.database.DbHelper;
import com.example.sadic.ecommerceapp.data.database.IDbHelper;
import com.example.sadic.ecommerceapp.data.database.model.CartProduct;

import java.util.List;

public class PresenterCart implements IPresenterCart, IDataManager.OnResponseListener {
    private static final String TAG = "PresenterCart";

    IViewCart iView;
    IDataManager dataManager;

    public PresenterCart(CartActivity cartActivity) {
        this.iView = cartActivity;
        this.dataManager = new DataManager(cartActivity);
    }

    @Override
    public void getActivityData() {
        Log.d(TAG, "getActivityData: started");
        dataManager.getAllData(this);
        dataManager.getCartOnlyData(this, 1);
    }

    @Override
    public void onButtonClicked(View view) {


    }


    @Override
    public void getCartProductList(List<CartProduct> cartProductList) {
        Log.d(TAG, "getCartProductList: cartProductList: " + cartProductList.toString());
        //iView.showCartList(cartProductList);
    }

    @Override
    public void getCartOnlyList(List<CartProduct> cartOnlyProductList) {
        Log.d(TAG, "getCartOnlyList: cartProductList: " + cartOnlyProductList);
        iView.showCartList(cartOnlyProductList);
    }


}
