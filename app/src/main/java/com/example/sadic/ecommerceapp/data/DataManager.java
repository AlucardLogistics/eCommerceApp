package com.example.sadic.ecommerceapp.data;

import android.content.Context;

import com.example.sadic.ecommerceapp.data.network.INetworkHelper;
import com.example.sadic.ecommerceapp.data.network.NetworkHelper;

public class DataManager implements IDataManager {

    INetworkHelper networkHelper;

    public DataManager(Context context) {
        networkHelper = new NetworkHelper();
    }


    @Override
    public void getCategories(OnResponseNetworkListener categoryListener) {

    }

    @Override
    public void getSubCategories(OnResponseNetworkListener subCategoryListener) {

    }

    @Override
    public void getProducts(OnResponseNetworkListener productListener) {
        networkHelper.getProducts(productListener);
    }
}
