package com.example.sadic.ecommerceapp.data.database.model;

import android.provider.BaseColumns;

public class CartContract {

    public CartContract() {

    }

    public static abstract class ProductEntry implements BaseColumns {
        public static final String TABLE_NAME = "cart_table";
        public static final String COLUMN_ID = "product_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_IMAGE = "image";
        public static final boolean COLUMN_IS_CART = false;
        public static final boolean COLUMN_IS_LIST = false;


    }

}
