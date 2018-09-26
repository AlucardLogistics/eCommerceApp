package com.example.sadic.ecommerceapp.ui.subcategory;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterSubModule {


    public SubCategoryActivity subCategoryActivity;

    public PresenterSubModule() {
        this.subCategoryActivity = new SubCategoryActivity();
    }

    public PresenterSubModule(SubCategoryActivity subCategoryActivity) {
        this.subCategoryActivity = subCategoryActivity;
    }



    @Provides //needed by provideSharedP
    public SubCategoryActivity provideSubcategoryActivity() {
        return subCategoryActivity;
    }


    //private SubCategoryActivity subCategoryActivity;


    @Provides
    public PresenterSubCategory getPresenterSubCategory(SubCategoryActivity subCategoryActivity) {
        return new PresenterSubCategory(subCategoryActivity);
    }
}
