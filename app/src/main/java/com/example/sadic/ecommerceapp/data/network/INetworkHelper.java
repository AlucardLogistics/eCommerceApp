package com.example.sadic.ecommerceapp.data.network;

import com.example.sadic.ecommerceapp.data.IDataManager;

public interface INetworkHelper {

    void getCategories(IDataManager.OnResponseCategoryListener categoryListener);
    void getSubCategories(IDataManager.OnResponseSubCategoryListener subCategoryListener);
    void getProducts(IDataManager.OnResponseProductListener productListener);


}
