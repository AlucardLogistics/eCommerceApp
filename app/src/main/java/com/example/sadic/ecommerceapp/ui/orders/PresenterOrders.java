package com.example.sadic.ecommerceapp.ui.orders;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.sadic.ecommerceapp.R;
import com.example.sadic.ecommerceapp.data.DataManager;
import com.example.sadic.ecommerceapp.data.IDataManager;
import com.example.sadic.ecommerceapp.data.database.model.CartProduct;
import com.example.sadic.ecommerceapp.ui.mainfeed.MainActivity;

import java.util.List;

public class PresenterOrders implements IPresenterOrders, IDataManager.OnResponseListener {
    private static final String TAG = "PresenterOrders";

    IViewOrders iView;
    IDataManager dataManager;
    Context context;

    public PresenterOrders(OrderActivity orderActivity) {
        this.iView = orderActivity;
        this.dataManager = new DataManager(orderActivity);
        this.context = orderActivity;
    }

    @Override
    public void getActivityData() {
        dataManager.getCartOnlyData(this, 1);
    }

    @Override
    public void onButtonClicked(View view) {
        Log.d(TAG, "onButtonClicked: ");
        switch (view.getId()) {
            case R.id.btOrder:
                dataManager.clearCart(this, 1);
                Log.d(TAG, "onButtonClicked: clearing the cart");
                Toast.makeText(context, "Order has been placed!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, MainActivity.class);
                context.startActivity(i);
                break;
        }
    }

    @Override
    public void getCartProductList(List<CartProduct> cartProductList) {

    }

    @Override
    public void getCartOnlyList(List<CartProduct> cartOnlyProductList) {
        iView.showCartList(cartOnlyProductList);
    }

    @Override
    public void clearCart(int cartCode) {

    }
}
