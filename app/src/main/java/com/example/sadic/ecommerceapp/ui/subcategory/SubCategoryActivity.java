package com.example.sadic.ecommerceapp.ui.subcategory;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sadic.ecommerceapp.R;
import com.example.sadic.ecommerceapp.adapters.RecyclerViewCategory;
import com.example.sadic.ecommerceapp.adapters.RecyclerViewSubCategory;
import com.example.sadic.ecommerceapp.data.network.model.SubCategory;

import java.util.List;

public class SubCategoryActivity extends AppCompatActivity implements IViewSubCategory {

    IPresenterSubCategory presenterSubCategory;
    RecyclerView rvSubCategory;
    RecyclerViewSubCategory adapter;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        presenterSubCategory = new PresenterSubCategory(this);
        presenterSubCategory.setActivityData();

        rvSubCategory = findViewById(R.id.rvSubCategory);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rvSubCategory.setLayoutManager(manager);
        rvSubCategory.setItemAnimator(new DefaultItemAnimator());


        pd = new ProgressDialog(this);
        pd.setTitle("My Progress Dialog");
        pd.setMessage("Fetching data from the database!");
        pd.setCancelable(false);
        showDialog();
    }

    @Override
    public void showSubCategoryList(List<SubCategory> subCategoryList) {
        adapter = new RecyclerViewSubCategory(this, subCategoryList);
        rvSubCategory.setAdapter(adapter);
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
