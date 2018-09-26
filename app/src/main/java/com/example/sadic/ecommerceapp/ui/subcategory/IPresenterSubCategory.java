package com.example.sadic.ecommerceapp.ui.subcategory;

import android.view.View;

import com.example.sadic.ecommerceapp.data.network.model.SubCategory;

import java.util.List;

public interface IPresenterSubCategory {

    void setActivityData();
    void onItemClick(View v, int position);
}
