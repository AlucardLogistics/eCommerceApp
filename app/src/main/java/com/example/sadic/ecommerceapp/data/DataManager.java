package com.example.sadic.ecommerceapp.data;

import android.content.Context;

import com.example.sadic.ecommerceapp.data.database.IDbHelper;
import com.example.sadic.ecommerceapp.data.database.DbHelper;
import com.example.sadic.ecommerceapp.data.database.model.CartProduct;
import com.example.sadic.ecommerceapp.data.network.INetworkHelper;
import com.example.sadic.ecommerceapp.data.network.NetworkHelper;

public class DataManager implements IDataManager {

    INetworkHelper networkHelper;
    IDbHelper dbHelper;

    public DataManager(Context context) {
        networkHelper = new NetworkHelper(context);
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
    public void deleteCartOrWishRowWithId(int id, String pId, int cartCode, int wishCode) {
        dbHelper.deleteCartOrWishRowWithId(id, pId, cartCode, wishCode);
    }

    @Override
    public void clearCart(OnResponseListener clearCartListener, int cartCode) {
        dbHelper.clearCart(clearCartListener, cartCode);
    }

    @Override
    public void getCartOnlyData(OnResponseListener cartListener, int cartCode) {
        dbHelper.getCartOnlyData(cartListener, cartCode);
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

    @Override
    public void login(OnLoginListener listenerLogin) {
        networkHelper.login(listenerLogin);
    }

    @Override
    public void register(OnRegisterListener listenerRegister) {
        networkHelper.register(listenerRegister);
    }


}
