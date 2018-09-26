package com.example.sadic.ecommerceapp.ui.subcategory;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.example.sadic.ecommerceapp.data.DataManager;
import com.example.sadic.ecommerceapp.data.IDataManager;
import com.example.sadic.ecommerceapp.data.network.model.SubCategory;

import java.util.List;



public class PresenterSubCategory extends PresenterSubModule implements IPresenterSubCategory, IDataManager.OnResponseSubCategoryListener {
    private static final String TAG = "PresenterSubCategory";


    IViewSubCategory iView;
    IDataManager dataManager;
    Context context;


    public PresenterSubCategory(SubCategoryActivity subCategoryActivity) {
        iView = subCategoryActivity;
        dataManager = new DataManager(subCategoryActivity);
    }



    @Override
    public void setActivityData() {
        Log.d(TAG, "setActivityData: started");
        dataManager.getSubCategories(this);
    }


    @Override
    public void onItemClick(View v, int position) {

    }


    @Override
    public void getSubCategories(List<SubCategory> subCategoryList) {
        iView.showSubCategoryList(subCategoryList);
    }
}
