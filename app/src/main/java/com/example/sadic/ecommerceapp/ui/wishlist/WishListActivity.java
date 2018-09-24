package com.example.sadic.ecommerceapp.ui.wishlist;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.sadic.ecommerceapp.R;
import com.example.sadic.ecommerceapp.adapters.RecyclerViewWishListAdapter;
import com.example.sadic.ecommerceapp.data.database.model.CartProduct;

import java.util.List;

public class WishListActivity extends AppCompatActivity implements IViewWishList {

    IPresenterWishList presenterWishList;
    RecyclerView rvWishList;
    RecyclerViewWishListAdapter adapter;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);

        init();

        presenterWishList = new PresenterWishList(this);
        presenterWishList.getActivityData();
    }

    private void init() {
        rvWishList = findViewById(R.id.rvWishListView);

//        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
//        rvCart.setLayoutManager(manager);
//        rvCart.setItemAnimator(new DefaultItemAnimator());

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvWishList.setLayoutManager(layoutManager);


        pd = new ProgressDialog(this);
        pd.setTitle("My Progress Dialog");
        pd.setMessage("Fetching data from the database!");
        pd.setCancelable(false);
        showDialog();
    }

    public void eventHandler(View view) {
        presenterWishList.onButtonClicked(view);
    }

    @Override
    public void showCartList(List<CartProduct> cartProductList) {
        adapter = new RecyclerViewWishListAdapter(this, cartProductList);
        rvWishList.setAdapter(adapter);
        dismissDialog();
    }

    private void showDialog() {
        if(!pd.isShowing()) {
            pd.show();
        }
    }

    private void dismissDialog() {
        if(pd.isShowing()) {
            pd.dismiss();
        }
    }
}
