package com.example.sadic.ecommerceapp.ui.product_detail;

import android.view.View;

import com.example.sadic.ecommerceapp.R;

public class PresenterDetail implements IPresenterDetail {

    IViewDetail iView;

    public PresenterDetail(DetailActivity detailActivity) {
        iView = detailActivity;
    }

    @Override
    public void getProductDetails() {

    }

    @Override
    public void onButtonClickHandler(View view) {
        switch (view.getId()) {
            case R.id.btAddCart:

                break;
        }
    }


}
