package com.example.sadic.ecommerceapp.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sadic.ecommerceapp.data.database.model.ProductContract.ProductEntry;
import com.example.sadic.ecommerceapp.data.network.model.Product;

public class DbOpenHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + ProductEntry.TABLE_NAME + " (" + ProductEntry._ID
                    + " INTEGER PRIMARY KEY," + ProductEntry.COLUMN_ID + " TEXT,"
                    + ProductEntry.COLUMN_NAME + " TEXT," + ProductEntry.COLUMN_QUANTITY
                    + " TEXT, " + ProductEntry.COLUMN_PRICE + " TEXT, " + ProductEntry.COLUMN_DESCRIPTION
                    + " TEXT, " + ProductEntry.COLUMN_IMAGE + "TEXT)";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ProductEntry.TABLE_NAME;


    public DbOpenHelper(Context context) {
        super(context, ProductEntry.TABLE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
