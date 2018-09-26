package com.example.sadic.ecommerceapp.data.database;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.sadic.ecommerceapp.data.IDataManager;
import com.example.sadic.ecommerceapp.data.database.model.CartContract;
import com.example.sadic.ecommerceapp.data.database.model.CartProduct;
import com.example.sadic.ecommerceapp.ui.mainfeed.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class DbHelper implements IDbHelper {
    private static final String TAG = "DbHelper";

    SQLiteDatabase database;
    DbOpenHelper dbOpenHelper;
    Context context;

    public DbHelper(Context context) {
        this.dbOpenHelper = new DbOpenHelper(context);
        this.database = dbOpenHelper.getWritableDatabase();
        this.context = context;
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
    public void createWishRow(CartProduct cartProduct, String pId, int wishCode) {
        Log.d(TAG, "createWishRow: started");
        String table = CartContract.ProductEntry.TABLE_NAME;
        String where = CartContract.ProductEntry.COLUMN_ID + "=? AND "
                + CartContract.ProductEntry.COLUMN_IS_WISHLIST + "=?";

        String[] columns = {CartContract.ProductEntry.COLUMN_ID, CartContract.ProductEntry.COLUMN_IS_WISHLIST};

        String[] whereArgs = new String[] {pId, String.valueOf(wishCode)};
        String query = "SELECT * FROM " + table + " WHERE " + columns + " =? AND =?";

        Cursor c = database.query(table, null, where, whereArgs,
                null, null, null);

//        Cursor cc = database.rawQuery(query, whereArgs);
        Log.d(TAG, "createWishRow: Cursor: " + c.toString());
        if(c.moveToFirst()) {
            Toast.makeText(context, "Product already added to wish list.", Toast.LENGTH_SHORT).show();
        } else {
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
            Toast.makeText(context, "Added to wish list.", Toast.LENGTH_SHORT).show();
        }
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
    public void deleteCartOrWishRowWithId(int id, String pId, int cartCode, int wishCode) {
        String table = CartContract.ProductEntry.TABLE_NAME;
        String where = CartContract.ProductEntry._ID + "=? AND "
                + CartContract.ProductEntry.COLUMN_ID + "=? AND "
                + CartContract.ProductEntry.COLUMN_IS_CART + "=? AND "
                + CartContract.ProductEntry.COLUMN_IS_WISHLIST + "=?";
        String[] whereArgs = new String[] {String.valueOf(id) ,pId, String.valueOf(cartCode), String.valueOf(wishCode)};
        database.delete(table, where, whereArgs);
    }

    @Override
    public void clearCart(IDataManager.OnResponseListener clearCartListener, int cartCode) {
        Log.d(TAG, "clearCart: clearing cart");
        String table = CartContract.ProductEntry.TABLE_NAME;
        String where = CartContract.ProductEntry.COLUMN_IS_CART + "=?";
        String[] whereArgs = new String[] {String.valueOf(cartCode)};
        database.delete(table, where, whereArgs);
        clearCartListener.clearCart(cartCode);
    }

    @Override
    public void getCartOnlyData(IDataManager.OnResponseListener cartListener, int cartCode) {
        Log.d(TAG, "getCartOnlyData: ");
        String table = CartContract.ProductEntry.TABLE_NAME;
        String columns = CartContract.ProductEntry.COLUMN_IS_CART;
        String[] whereArgs = new String[] {String.valueOf(cartCode)};

        String query = "SELECT * FROM " + table + " WHERE " + columns + " =? ";
        Cursor c = database.rawQuery(query, whereArgs);
        List<CartProduct> cartProductList = new ArrayList<>();
        CartProduct cartProduct;
        int idTableColumnIndex = c.getColumnIndexOrThrow(CartContract.ProductEntry._ID);
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
                cartProduct = new CartProduct(c.getString(idTableColumnIndex),c.getString(idColumnIndex),
                        c.getString(nameColumnIndex), c.getString(quantityColumnIndex),
                        c.getString(priceColumnIndex), c.getString(descriptionColumnIndex),
                        c.getString(imagePathColumnIndex), c.getInt(isCartColumnIndex),
                        c.getInt(isWishColumnIndex));
                Log.d(TAG, "getAllData: cartProduct: " + cartProduct.toString());
                cartProductList.add(cartProduct);
                cartListener.getCartOnlyList(cartProductList);
            } while (c.moveToNext());
        } else {
            cartListener.getCartOnlyList(cartProductList);
            Log.d(TAG, "getCartOnlyData: else stuff");
        }
        c.close();

    }




    @Override
    public void getAllData(IDataManager.OnResponseListener listener) {
        Log.d(TAG, "getAllData: started");

        //Cursor cu = database.rawQuery("SELECT * FROM " + CartContract.ProductEntry.TABLE_NAME, null);
        Cursor c = database.query(CartContract.ProductEntry.TABLE_NAME, null, null, null, null, null, null);
        List<CartProduct> cartProductList = new ArrayList<>();
        CartProduct cartProduct;
        int idTableColumnIndex = c.getColumnIndexOrThrow(CartContract.ProductEntry._ID);
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
                cartProduct = new CartProduct(c.getString(idTableColumnIndex),c.getString(idColumnIndex),
                        c.getString(nameColumnIndex), c.getString(quantityColumnIndex),
                        c.getString(priceColumnIndex), c.getString(descriptionColumnIndex),
                        c.getString(imagePathColumnIndex), c.getInt(isCartColumnIndex),
                        c.getInt(isWishColumnIndex));
                Log.d(TAG, "getAllData: cartProduct: " + cartProduct.toString());
                cartProductList.add(cartProduct);
                listener.getCartProductList(cartProductList);
            } while (c.moveToNext());
        }

        c.close();
    }

}
