package com.example.sadic.ecommerceapp.data.network;

import com.example.sadic.ecommerceapp.data.IDataManager;

public interface INetworkHelper {

    void getCategories(IDataManager.OnResponseNetworkListener categoryListener);
    void getSubCategories(IDataManager.OnResponseNetworkListener subCategoryListener);
    void getProducts(IDataManager.OnResponseNetworkListener productListener);


}
