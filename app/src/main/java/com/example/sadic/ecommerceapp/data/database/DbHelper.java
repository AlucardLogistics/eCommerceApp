package com.example.sadic.ecommerceapp.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sadic.ecommerceapp.data.IDataManager;
import com.example.sadic.ecommerceapp.data.database.model.CartContract;
import com.example.sadic.ecommerceapp.data.database.model.CartProduct;

import java.util.ArrayList;
import java.util.List;

public class DbHelper implements IDbHelper {
    private static final String TAG = "DbHelper";

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
        //Cursor cursor = database.rawQuery();
    }

    @Override
    public void updateRow() {

    }

    @Override
    public void deleteCartOrWishRow(String name, int cartCode, int wishCode) {
        String table = CartContract.ProductEntry.TABLE_NAME;
        String where = CartContract.ProductEntry.COLUMN_NAME + "=? AND "
                + CartContract.ProductEntry.COLUMN_IS_CART + "=? AND "
                + CartContract.ProductEntry.COLUMN_IS_WISHLIST + "=?";
        String[] whereArgs = new String[] {name, String.valueOf(cartCode), String.valueOf(wishCode)};
        database.delete(table, where, whereArgs);
    }

    @Override
    public void getAllData(IDataManager.OnResponseListener listener) {
        Log.d(TAG, "getAllData: started");

        Cursor cu = database.rawQuery("SELECT * FROM " + CartContract.ProductEntry.TABLE_NAME, null);
        Cursor c = database.query(CartContract.ProductEntry.TABLE_NAME, null, null, null, null, null, null);
        List<CartProduct> cartProductList = new ArrayList<>();
        CartProduct cartProduct;

        int idColumnIndex = c.getColumnIndexOrThrow(CartContract.ProductEntry.COLUMN_ID);
        int nameColumnIndex = c.getColumnIndexOrThrow(CartContract.ProductEntry.COLUMN_NAME);
        int quantityColumnIndex = c.getColumnIndexOrThrow(CartContract.ProductEntry.COLUMN_QUANTITY);
        int priceColumnIndex = c.getColumnIndexOrThrow(CartContract.ProductEntry.COLUMN_PRICE);
        int descriptionColumnIndex = c.getColumnIndexOrThrow(CartContract.ProductEntry.COLUMN_DESCRIPTION);
        int imagePathColumnIndex = c.getColumnIndexOrThrow(CartContract.ProductEntry.COLUMN_IMAGE_PATH);
        int isCartColumnIndex = c.getColumnIndexOrThrow(CartContract.ProductEntry.COLUMN_IS_CART);
        int isWishColumnIndex = c.getColumnIndexOrThrow(CartContract.ProductEntry.COLUMN_IS_WISHLIST);

        //loop
        if(c != null && c.moveToFirst()) {
            do {
                cartProduct = new CartProduct(c.getString(idColumnIndex),
                        c.getString(nameColumnIndex), c.getString(quantityColumnIndex),
                        c.getString(priceColumnIndex), c.getString(descriptionColumnIndex),
                        c.getString(imagePathColumnIndex), c.getInt(isCartColumnIndex),
                        c.getInt(isWishColumnIndex));
                Log.d(TAG, "getAllData: cartProduct: " + cartProduct.toString());
                cartProductList.add(cartProduct);
                listener.getCartProductList(cartProductList);
            } while (c.moveToNext());
        }
    }

//    @Override
//    public void getAllData(IDataManager.OnResponseListener listener) {
//
//        Cursor c = database.query(CartContract.ProductEntry.TABLE_NAME, null, null, null, null, null, null);
//        CartProduct cartProduct = null;
//
//        int idColumnIndex = c.getColumnIndexOrThrow(CartContract.ProductEntry.COLUMN_ID);
//        int nameColumnIndex = c.getColumnIndexOrThrow(CartContract.ProductEntry.COLUMN_NAME);
//        int quantityColumnIndex = c.getColumnIndexOrThrow(CartContract.ProductEntry.COLUMN_QUANTITY);
//        int priceColumnIndex = c.getColumnIndexOrThrow(CartContract.ProductEntry.COLUMN_PRICE);
//        int descriptionColumnIndex = c.getColumnIndexOrThrow(CartContract.ProductEntry.COLUMN_DESCRIPTION);
//        int imagePathColumnIndex = c.getColumnIndexOrThrow(CartContract.ProductEntry.COLUMN_IMAGE_PATH);
//        int isCartColumnIndex = c.getColumnIndexOrThrow(CartContract.ProductEntry.COLUMN_IS_CART);
//        int isWishColumnIndex = c.getColumnIndexOrThrow(CartContract.ProductEntry.COLUMN_IS_WISHLIST);
//
//        //loop
//        if(c != null && c.moveToFirst()) {
//            do {
//                cartProduct = new CartProduct(c.getString(idColumnIndex),
//                        c.getString(nameColumnIndex), c.getString(quantityColumnIndex),
//                        c.getString(priceColumnIndex), c.getString(descriptionColumnIndex),
//                        c.getString(imagePathColumnIndex), c.getInt(isCartColumnIndex),
//                        c.getInt(isWishColumnIndex));
//            } while (c.moveToNext());
//        }
//
//        listener.getCartProduct(cartProduct);
//
//    }

}
