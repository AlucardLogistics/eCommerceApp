package com.example.sadic.ecommerceapp.ui.product_detail;


import android.content.Context;
import android.view.View;

import com.example.sadic.ecommerceapp.data.database.model.CartProduct;

public interface IPresenterDetail {

    void getProductDetails();
    void onButtonClickHandler(View view, CartProduct cartProduct);
}
