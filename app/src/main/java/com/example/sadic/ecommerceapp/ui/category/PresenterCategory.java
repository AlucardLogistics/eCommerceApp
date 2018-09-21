package com.example.sadic.ecommerceapp.ui.category;

import com.example.sadic.ecommerceapp.data.DataManager;
import com.example.sadic.ecommerceapp.data.IDataManager;
import com.example.sadic.ecommerceapp.data.network.model.Category;

import java.util.List;

public class PresenterCategory implements IPresenterCategory, IDataManager.OnResponseCategoryListener {

    IViewCategory iView;
    IDataManager dataManager;

    public PresenterCategory(CategoryActivity categoryActivity) {
        iView = categoryActivity;
        dataManager = new DataManager(categoryActivity);

    }


    @Override
    public void setActivityData() {
        dataManager.getCategories(this);
    }


    @Override
    public void getCategories(List<Category> categoryList) {
        iView.showCategoryList(categoryList);
    }
}
