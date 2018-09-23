package com.example.sadic.ecommerceapp.data.database;

import android.database.Cursor;

import com.example.sadic.ecommerceapp.data.IDataManager;
import com.example.sadic.ecommerceapp.data.database.model.CartProduct;

import java.util.List;

public interface IDbHelper {

    void createRow(CartProduct cartProduct);
    void readRow();
    void updateRow();
    void deleteRow();
    void getAllData(IDataManager.OnResponseListener listener);
}
