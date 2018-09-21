package com.example.sadic.ecommerceapp.ui.category;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sadic.ecommerceapp.R;
import com.example.sadic.ecommerceapp.adapters.RecyclerViewCategory;
import com.example.sadic.ecommerceapp.data.network.model.Category;

import java.util.List;

public class CategoryActivity extends AppCompatActivity implements IViewCategory{

    IPresenterCategory presenterCategory;
    RecyclerView rvCategory;
    RecyclerViewCategory adapter;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        presenterCategory = new PresenterCategory(this);
        presenterCategory.setActivityData();

        rvCategory = findViewById(R.id.rvCategory);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rvCategory.setLayoutManager(manager);
        rvCategory.setItemAnimator(new DefaultItemAnimator());


        pd = new ProgressDialog(this);
        pd.setTitle("My Progress Dialog");
        pd.setMessage("Fetching data from the database!");
        pd.setCancelable(false);
        showDialog();

    }

    @Override
    public void showCategoryList(List<Category> categoryList) {
        adapter = new RecyclerViewCategory(this, categoryList);
        rvCategory.setAdapter(adapter);
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
