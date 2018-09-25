package com.example.sadic.ecommerceapp.ui.mainfeed;

import android.content.Context;

import com.example.sadic.ecommerceapp.data.DataManager;
import com.example.sadic.ecommerceapp.data.IDataManager;
import com.example.sadic.ecommerceapp.data.network.model.Product;

import java.util.List;

public class PresenterMain implements IPresenterMain, IDataManager.OnResponseProductListener {

    IViewMain mView;
    IDataManager dataManager;
    Context context;

    public PresenterMain(MainActivity mainActivity) {

        dataManager = new DataManager(mainActivity);
        mView = mainActivity;
        context = mainActivity.getApplicationContext();

    }

    //presenter retrieves data from managers
    @Override
    public void onCreateActivity() {
        dataManager.getProducts(this);
    }


    @Override
    public void getProducts(List<Product> productList) {
        mView.showProductList(productList);
    }

}
