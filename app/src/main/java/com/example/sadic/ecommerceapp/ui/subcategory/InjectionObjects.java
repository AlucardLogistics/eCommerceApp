package com.example.sadic.ecommerceapp.ui.subcategory;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {PresenterSubModule.class})
public interface InjectionObjects {

    void inject(SubCategoryActivity subCategoryActivity);
}
