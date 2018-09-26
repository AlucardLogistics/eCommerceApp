package com.example.sadic.ecommerceapp.data;

import com.example.sadic.ecommerceapp.data.database.IDbHelper;
import com.example.sadic.ecommerceapp.data.database.model.CartProduct;
import com.example.sadic.ecommerceapp.data.network.INetworkHelper;
import com.example.sadic.ecommerceapp.data.network.model.Category;
import com.example.sadic.ecommerceapp.data.network.model.Product;
import com.example.sadic.ecommerceapp.data.network.model.SubCategory;
import com.example.sadic.ecommerceapp.data.network.model.User;

import java.util.List;

public interface IDataManager extends INetworkHelper, IDbHelper {

    interface OnResponseListener {
        //void getCartProduct(CartProduct cartProduct);
        void getCartProductList(List<CartProduct> cartProductList);
        void getCartOnlyList(List<CartProduct> cartOnlyProductList);
        void clearCart(int cartCode);
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

    interface OnLoginListener {
        void userLogin(String mobile, String password);
    }

    interface OnRegisterListener {
        void userRegister(User userRegister);
    }
}
