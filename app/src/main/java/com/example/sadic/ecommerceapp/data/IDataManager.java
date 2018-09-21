package com.example.sadic.ecommerceapp.data;

import com.example.sadic.ecommerceapp.data.network.INetworkHelper;
import com.example.sadic.ecommerceapp.data.network.model.Product;

import java.util.List;

public interface IDataManager extends INetworkHelper {

    interface OnResponseListener {

    }

    interface OnResponseNetworkListener {

        void getCategories();
        void getProducts(List<Product> productList);
        void getSubCategories();
    }
}
