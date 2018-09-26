package com.example.sadic.ecommerceapp.ui.category;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sadic.ecommerceapp.R;
import com.example.sadic.ecommerceapp.adapters.RecyclerViewCategoryAdapter;
import com.example.sadic.ecommerceapp.data.network.model.Category;

import java.util.List;

public class CategoryActivity extends AppCompatActivity implements IViewCategory{

    IPresenterCategory presenterCategory;
    RecyclerView rvCategory;
    RecyclerViewCategoryAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        init();

        presenterCategory = new PresenterCategory(this);
        presenterCategory.setActivityData();

    }

    void init() {
        rvCategory = findViewById(R.id.rvCategory);

        RecyclerView.LayoutManager manager = new GridLayoutManager(this, 3);
        rvCategory.setLayoutManager(manager);
        rvCategory.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public void showCategoryList(List<Category> categoryList) {
        adapter = new RecyclerViewCategoryAdapter(this, categoryList);
        rvCategory.setAdapter(adapter);
        //dismissDialog();
    }

}
