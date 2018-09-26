package com.example.sadic.ecommerceapp.ui.orders;

import com.example.sadic.ecommerceapp.data.database.model.CartProduct;

import java.util.List;

public interface IViewOrders {

    void showCartList(List<CartProduct> cartProductList);
}
