package com.example.sadic.ecommerceapp.data.database;

import android.database.Cursor;

import com.example.sadic.ecommerceapp.data.IDataManager;
import com.example.sadic.ecommerceapp.data.database.model.CartProduct;

import java.util.List;

public interface IDbHelper {

    void createRow(CartProduct cartProduct);
    void createWishRow(CartProduct cartProduct,String pId, int wishCode);
    void readRow();
    void updateRow();
    //void deleteCartRow(String pId, int cartCode);
    void deleteCartOrWishRow(String name, int cartCode, int wishCode);
    void deleteCartOrWishRowWithId(int id, String pId, int cartCode, int wishCode);
    void clearCart(IDataManager.OnResponseListener clearCartListener, int cartCode);
    void getCartOnlyData(IDataManager.OnResponseListener cartListener, int cartCode);
    void getAllData(IDataManager.OnResponseListener listener);
}
