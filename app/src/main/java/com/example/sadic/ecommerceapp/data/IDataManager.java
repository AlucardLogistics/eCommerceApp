package com.example.sadic.ecommerceapp.data;

import com.example.sadic.ecommerceapp.data.network.INetworkHelper;
import com.example.sadic.ecommerceapp.data.network.model.Category;
import com.example.sadic.ecommerceapp.data.network.model.Product;
import com.example.sadic.ecommerceapp.data.network.model.SubCategory;

import java.util.List;

public interface IDataManager extends INetworkHelper {

    interface OnResponseListener {

    }

    interface OnResponseProductListener {
        void getProducts(List<Product> productList);
    }

    interface OnResponseCategoryListener {
        void getCategories(List<Category> categoryList);
    }

    interface OnResponseSubCategoryListener {
        void getSubCategories(List<SubCategory> subCategoryList);
    }
}
