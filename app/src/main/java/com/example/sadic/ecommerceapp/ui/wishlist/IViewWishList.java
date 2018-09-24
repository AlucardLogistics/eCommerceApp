package com.example.sadic.ecommerceapp.ui.wishlist;

import com.example.sadic.ecommerceapp.data.database.model.CartProduct;

import java.util.List;

public interface IViewWishList {

    void showCartList(List<CartProduct> cartProductList);
}
