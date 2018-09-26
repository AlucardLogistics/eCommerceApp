package com.example.sadic.ecommerceapp.ui.product_detail;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.example.sadic.ecommerceapp.R;
import com.example.sadic.ecommerceapp.data.DataManager;
import com.example.sadic.ecommerceapp.data.IDataManager;
import com.example.sadic.ecommerceapp.data.database.model.CartProduct;
import com.example.sadic.ecommerceapp.ui.mainfeed.MainActivity;

import java.util.ConcurrentModificationException;

public class PresenterDetail implements IPresenterDetail {
    private static final String TAG = "PresenterDetail";

    IViewDetail iView;
    IDataManager dataManager;
    //Context context;

    public PresenterDetail(DetailActivity detailActivity) {
        iView = detailActivity;
        dataManager = new DataManager(detailActivity);
    }

    @Override
    public void getProductDetails() {

    }

    @Override
    public void onButtonClickHandler(View view, CartProduct cartProduct, Context context) {
        Log.d(TAG, "onButtonClickHandler: started" + cartProduct.toString());
        switch (view.getId()) {
            case R.id.btAddCart:
                dataManager.createRow(cartProduct);
                Intent mainFeedIntent = new Intent(context, MainActivity.class);
                context.startActivity(mainFeedIntent);
                break;
            case R.id.btWishList:
                dataManager.createRow(cartProduct);
                Intent mainIntent = new Intent(context, MainActivity.class);
                context.startActivity(mainIntent);
                break;
        }
    }


}
