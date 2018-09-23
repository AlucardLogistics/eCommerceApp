package com.example.sadic.ecommerceapp.ui.cart;

import com.example.sadic.ecommerceapp.data.database.model.CartProduct;

import java.util.List;

public interface IViewCart {

    void showCartList(List<CartProduct> cartProductList);


}
