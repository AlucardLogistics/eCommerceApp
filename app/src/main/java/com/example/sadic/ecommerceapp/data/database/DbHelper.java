package com.example.sadic.ecommerceapp.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.sadic.ecommerceapp.data.database.model.CartContract;
import com.example.sadic.ecommerceapp.data.database.model.CartProduct;

public class DbHelper implements IDbHelper {

    SQLiteDatabase database;
    DbOpenHelper dbOpenHelper;

    public DbHelper(Context context) {
        this.dbOpenHelper = new DbOpenHelper(context);
        this.database = dbOpenHelper.getWritableDatabase();
    }

    @Override
    public void createRow(CartProduct cartProduct) {
        ContentValues cv = new ContentValues();
        cv.put(CartContract.ProductEntry.COLUMN_ID, cartProduct.getpId());
        cv.put(CartContract.ProductEntry.COLUMN_NAME, cartProduct.getpName());
        cv.put(CartContract.ProductEntry.COLUMN_QUANTITY, cartProduct.getpQuantity());
        cv.put(CartContract.ProductEntry.COLUMN_PRICE, cartProduct.getpPrice());
        cv.put(CartContract.ProductEntry.COLUMN_DESCRIPTION, cartProduct.getpDescription());
        cv.put(CartContract.ProductEntry.COLUMN_IMAGE_PATH, cartProduct.getpThumbPath());
        cv.put(CartContract.ProductEntry.COLUMN_IS_CART, cartProduct.getIsCart());
        cv.put(CartContract.ProductEntry.COLUMN_IS_WISHLIST, cartProduct.getIsWish());
        database.insert(CartContract.ProductEntry.TABLE_NAME, null, cv);
    }

    @Override
    public void readRow() {

    }

    @Override
    public void updateRow() {

    }

    @Override
    public void deleteRow() {

    }
}
