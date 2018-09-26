package com.example.sadic.ecommerceapp.ui.subcategory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.sadic.ecommerceapp.R;
import com.example.sadic.ecommerceapp.adapters.RecyclerViewSubCategoryAdapter;
import com.example.sadic.ecommerceapp.data.network.model.SubCategory;
import com.example.sadic.ecommerceapp.ui.mainfeed.MainActivity;

import java.util.List;

public class SubCategoryActivity extends AppCompatActivity implements IViewSubCategory {

    IPresenterSubCategory presenterSubCategory;
    RecyclerView rvSubCategory;
    RecyclerViewSubCategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        init();

        presenterSubCategory = new PresenterSubCategory(this);
        presenterSubCategory.setActivityData();
    }

    void init() {
        rvSubCategory = findViewById(R.id.rvSubCategory);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rvSubCategory.setLayoutManager(manager);
        rvSubCategory.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public void showSubCategoryList(List<SubCategory> subCategoryList) {
        //adapter = new RecyclerViewSubCategoryAdapter(this, subCategoryList);
        adapter = new RecyclerViewSubCategoryAdapter(subCategoryList, new IPresenterSubCategory() {
            @Override
            public void setActivityData() {

            }

            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(SubCategoryActivity.this, "CLicked" + position, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(SubCategoryActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        rvSubCategory.setAdapter(adapter);
        //dismissDialog();
    }

}
