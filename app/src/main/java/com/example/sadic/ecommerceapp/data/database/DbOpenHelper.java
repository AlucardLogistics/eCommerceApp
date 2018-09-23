package com.example.sadic.ecommerceapp.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sadic.ecommerceapp.data.database.model.CartContract.ProductEntry;

public class DbOpenHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + ProductEntry.TABLE_NAME + " ("
                    + ProductEntry._ID + " INTEGER PRIMARY KEY,"
                    + ProductEntry.COLUMN_ID + " TEXT,"
                    + ProductEntry.COLUMN_NAME + " TEXT,"
                    + ProductEntry.COLUMN_QUANTITY + " TEXT,"
                    + ProductEntry.COLUMN_PRICE + " TEXT,"
                    + ProductEntry.COLUMN_DESCRIPTION + " TEXT,"
                    + ProductEntry.COLUMN_IMAGE_PATH + " TEXT, "
                    + ProductEntry.COLUMN_IS_CART + " INTEGER DEFAULT 0,"
                    + ProductEntry.COLUMN_IS_WISHLIST + " INTEGER DEFAULT 0)";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ProductEntry.TABLE_NAME;


    public DbOpenHelper(Context context) {
        super(context, ProductEntry.TABLE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
