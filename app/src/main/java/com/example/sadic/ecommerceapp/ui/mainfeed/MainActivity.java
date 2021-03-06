package com.example.sadic.ecommerceapp.ui.mainfeed;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sadic.ecommerceapp.adapters.RecyclerViewProductAdapter;
import com.example.sadic.ecommerceapp.data.database.DbHelper;
import com.example.sadic.ecommerceapp.data.database.model.CartProduct;
import com.example.sadic.ecommerceapp.ui.cart.CartActivity;
import com.example.sadic.ecommerceapp.ui.category.CategoryActivity;
import com.example.sadic.ecommerceapp.R;
import com.example.sadic.ecommerceapp.data.network.model.Product;
import com.example.sadic.ecommerceapp.ui.login.LoginActivity;
import com.example.sadic.ecommerceapp.ui.wishlist.WishListActivity;
import com.example.sadic.ecommerceapp.utils.SharedPref;


import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IViewMain {
    private static final String TAG = "MainActivity";

    RecyclerView recyclerView;
    RecyclerViewProductAdapter adapter;
    IPresenterMain presenterMain;
    TextView tvMenuName, tvMenuEmail, tvItemCount;
    int cartCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();

        presenterMain = new PresenterMain(this);
        presenterMain.onCreateActivity();


    }

    void init() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.rvProduct);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        //setup name and email of nav bar
        View header = navigationView.getHeaderView(0);
        tvMenuName = header.findViewById(R.id.tvMenuName);
        tvMenuEmail = header.findViewById(R.id.tvMenuEmail);

        SharedPref.init(this);
        tvMenuName.setText(SharedPref.read(SharedPref.FIRST_NAME, null) + " "
                + SharedPref.read(SharedPref.LAST_NAME,null));
        tvMenuEmail.setText(SharedPref.read(SharedPref.EMAIL, null));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu: started");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        View cartTest = menu.findItem(R.id.action_cart).getActionView();
        tvItemCount = cartTest.findViewById(R.id.cart_badge);
        setupBadge();
        tvItemCount.setText(String.valueOf(cartCount));
        tvItemCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cartIntent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(cartIntent);
            }
        });

        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: started");
        setupBadge();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
        setupBadge();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

    public int setupBadge() {
        SharedPref.init(this);
        cartCount = SharedPref.read(SharedPref.CART_ITEMS, 0);
        Log.d(TAG, "setupBadge: cartCOunt: " + cartCount);
//        if(tvItemCount != null) {
//            if(cartCount == 0) {
//                if(tvItemCount.getVisibility() != View.GONE) {
//                    tvItemCount.setVisibility(View.GONE);
//                }
//            } else {
//                tvItemCount.setText(String.valueOf(cartCount));
//                if(tvItemCount.getVisibility() != View.VISIBLE) {
//                    tvItemCount.setVisibility(View.VISIBLE);
//                }
//            }
//        }
        return cartCount;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            Toast.makeText(this, "search", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_categories) {
            Toast.makeText(this, "categories", Toast.LENGTH_SHORT).show();
            Intent categoryIntent = new Intent(MainActivity.this, CategoryActivity.class);
            startActivity(categoryIntent);
            return true;
        } else if (id == R.id.action_cart) {
            Toast.makeText(this, "cart", Toast.LENGTH_SHORT).show();
            Intent cartIntent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(cartIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_search) {
            Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_home) {
            Intent homeIntent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(homeIntent);
        } else if (id == R.id.nav_cart) {
            Intent cartIntent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(cartIntent);
        } else if (id == R.id.nav_categories) {
            Intent categoryIntent = new Intent(MainActivity.this, CategoryActivity.class);
            startActivity(categoryIntent);
        } else if (id == R.id.nav_order_history) {

        } else if (id == R.id.nav_wish_list) {
            Intent wishListIntent = new Intent(MainActivity.this, WishListActivity.class);
            startActivity(wishListIntent);

        } else if (id == R.id.nav_viewed_items) {

        } else if (id == R.id.nav_account) {
            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(loginIntent);
        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_contact) {

        } else if (id == R.id.nav_logout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void showProductList(List<Product> productList) {
        adapter = new RecyclerViewProductAdapter(this, productList);
        recyclerView.setAdapter(adapter);
        //dismissDialog();

    }
}
