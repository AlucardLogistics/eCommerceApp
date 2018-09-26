package com.example.sadic.ecommerceapp.ui.subcategory;

import android.view.View;

import com.example.sadic.ecommerceapp.data.network.model.SubCategory;

import java.util.List;

import dagger.Module;
import dagger.Provides;


public interface IPresenterSubCategory {


    void setActivityData();

    void onItemClick(View v, int position);
}
