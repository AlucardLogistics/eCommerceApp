package com.example.sadic.ecommerceapp.ui.wishlist;

import android.view.View;

import com.example.sadic.ecommerceapp.data.DataManager;
import com.example.sadic.ecommerceapp.data.IDataManager;
import com.example.sadic.ecommerceapp.data.database.model.CartProduct;

import java.util.List;

public class PresenterWishList implements IPresenterWishList, IDataManager.OnResponseListener {

    IViewWishList iView;
    IDataManager dataManager;

    public PresenterWishList(WishListActivity wishListActivity) {
        this.iView = wishListActivity;
        this.dataManager = new DataManager(wishListActivity);
    }

    @Override
    public void getActivityData() {
        //dataManager.getAllData(this);
        dataManager.getCartOnlyData(this, 0);
    }

    @Override
    public void onButtonClicked(View view) {

    }

    @Override
    public void getCartProductList(List<CartProduct> cartProductList) {
        //iView.showCartList(cartProductList);
    }

    @Override
    public void getCartOnlyList(List<CartProduct> cartOnlyProductList) {
        iView.showCartList(cartOnlyProductList);
    }


}
