package com.example.sadic.ecommerceapp.ui.mainfeed;

import com.example.sadic.ecommerceapp.data.DataManager;
import com.example.sadic.ecommerceapp.data.IDataManager;
import com.example.sadic.ecommerceapp.data.network.model.Product;

import java.util.List;

public class PresenterMain implements IPresenterMain, IDataManager.OnResponseProductListener {

    IViewMain mView;
    IDataManager dataManager;

    public PresenterMain(MainActivity mainActivity) {

        dataManager = new DataManager(mainActivity);
        mView = mainActivity;

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
