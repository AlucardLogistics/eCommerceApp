package com.example.sadic.ecommerceapp.ui.subcategory;

import com.example.sadic.ecommerceapp.data.DataManager;
import com.example.sadic.ecommerceapp.data.IDataManager;
import com.example.sadic.ecommerceapp.data.network.model.SubCategory;

import java.util.List;

public class PresenterSubCategory implements IPresenterSubCategory, IDataManager.OnResponseSubCategoryListener {

    IViewSubCategory iView;
    IDataManager dataManager;

    public PresenterSubCategory(SubCategoryActivity subCategoryActivity) {
        iView = subCategoryActivity;
        dataManager = new DataManager(subCategoryActivity);
    }


    @Override
    public void setActivityData() {
        dataManager.getSubCategories(this);
    }

    @Override
    public void getSubCategories(List<SubCategory> subCategoryList) {
        iView.showSubCategoryList(subCategoryList);
    }
}
