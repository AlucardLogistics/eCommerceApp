package com.example.sadic.ecommerceapp.data.database;

import com.example.sadic.ecommerceapp.data.database.model.CartProduct;

public interface IDbHelper {

    void createRow(CartProduct cartProduct);
    void readRow();
    void updateRow();
    void deleteRow();
}
