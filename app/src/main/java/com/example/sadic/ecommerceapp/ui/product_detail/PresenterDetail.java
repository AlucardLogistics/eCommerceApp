package com.example.sadic.ecommerceapp.ui.product_detail;

import android.util.Log;
import android.view.View;

import com.example.sadic.ecommerceapp.R;
import com.example.sadic.ecommerceapp.data.DataManager;
import com.example.sadic.ecommerceapp.data.IDataManager;
import com.example.sadic.ecommerceapp.data.database.model.CartProduct;

public class PresenterDetail implements IPresenterDetail {
    private static final String TAG = "PresenterDetail";

    IViewDetail iView;
    IDataManager dataManager;

    public PresenterDetail(DetailActivity detailActivity) {
        iView = detailActivity;
        dataManager = new DataManager(detailActivity);
    }

    @Override
    public void getProductDetails() {

    }

    @Override
    public void onButtonClickHandler(View view, CartProduct cartProduct) {
        Log.d(TAG, "onButtonClickHandler: started" + cartProduct.toString());
        switch (view.getId()) {
            case R.id.btAddCart:
                dataManager.createRow(cartProduct);
                break;
            case R.id.btWishList:
                dataManager.createRow(cartProduct);
                break;
        }
    }


}
