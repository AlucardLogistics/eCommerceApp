package com.example.sadic.ecommerceapp.data;

import android.content.Context;

import com.example.sadic.ecommerceapp.data.database.IDbHelper;
import com.example.sadic.ecommerceapp.data.database.DbHelper;
import com.example.sadic.ecommerceapp.data.database.model.CartProduct;
import com.example.sadic.ecommerceapp.data.network.INetworkHelper;
import com.example.sadic.ecommerceapp.data.network.NetworkHelper;

import java.util.List;

public class DataManager implements IDataManager {

    INetworkHelper networkHelper;
    IDbHelper dbHelper;

    public DataManager(Context context) {
        networkHelper = new NetworkHelper();
        dbHelper = new DbHelper(context);
    }

    /* SQLite IMPLEMENTATION */

    @Override
    public void createRow(CartProduct cartProduct) {
        dbHelper.createRow(cartProduct);
    }

    @Override
    public void readRow() {

    }

    @Override
    public void updateRow() {

    }

    @Override
    public void deleteCartOrWishRow(String pId, int cartCode, int wishCode) {
        dbHelper.deleteCartOrWishRow(pId, cartCode, wishCode);
    }


    @Override
    public void getAllData(OnResponseListener listener) {
        dbHelper.getAllData(listener);
    }

    /* NETWORK IMPLEMENTATION */

    @Override
    public void getCategories(OnResponseCategoryListener categoryListener) {
        networkHelper.getCategories(categoryListener);
    }

    @Override
    public void getSubCategories(OnResponseSubCategoryListener subCategoryListener) {
        networkHelper.getSubCategories(subCategoryListener);
    }

    @Override
    public void getProducts(OnResponseProductListener productListener) {
        networkHelper.getProducts(productListener);
    }


}
