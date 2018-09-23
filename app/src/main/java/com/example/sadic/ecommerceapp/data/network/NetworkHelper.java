package com.example.sadic.ecommerceapp.data.network;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sadic.ecommerceapp.data.network.model.Category;
import com.example.sadic.ecommerceapp.data.network.model.SubCategory;
import com.example.sadic.ecommerceapp.utils.AppController;
import com.example.sadic.ecommerceapp.data.IDataManager;
import com.example.sadic.ecommerceapp.data.network.model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NetworkHelper implements INetworkHelper {
    private static final String TAG = "NetworkHelper";


    @Override
    public void getCategories(final IDataManager.OnResponseCategoryListener categoryListener) {
        String categoryListJSON = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_category.php?api_key=5fcb3d85f0ce5afb02618973b9e17919&user_id=1385";

        final JsonObjectRequest jsonObject = new JsonObjectRequest(
                Request.Method.GET,
                categoryListJSON,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse: started");
                        try {
                            JSONArray categoryArray = response.getJSONArray("category");
                            //new variables
                            Category category;
                            //RecyclerView rvProduct = null;
                            List<Category> categoryList = new ArrayList<>();
                            //RecyclerViewProductAdapter adapter = new RecyclerViewProductAdapter(productList);


                            for(int i = 0; i < categoryArray.length(); i ++) {
                                Log.d(TAG, "onResponse: response length: " + response.length());

                                JSONObject products = categoryArray.getJSONObject(i);

                                String cId = products.getString("cid");
                                String cName = products.getString("cname");
                                String cDescription = products.getString("cdiscription");
                                String cThumbImgUrl = products.getString("cimagerl");

                                category =
                                        new Category(cId, cName, cDescription, cThumbImgUrl);
                                categoryList.add(category);
                                //add response
                                categoryListener.getCategories(categoryList);
                                //rvProduct.setAdapter(adapter);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d(TAG, "onResponse: started");
                            //Toast.makeText(NetworkHelper.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        //dismissDialog();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(MainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        //dismissDialog();

                    }
                }
        );
        AppController.getInstance().addToRequestQueue(jsonObject);

    }

    @Override
    public void getSubCategories(final IDataManager.OnResponseSubCategoryListener subCategoryListener) {
        String subCategoryListJSON = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_sub_category.php?Id=107&api_key=5fcb3d85f0ce5afb02618973b9e17919&user_id=1385";

        final JsonObjectRequest jsonObject = new JsonObjectRequest(
                Request.Method.GET,
                subCategoryListJSON,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse: started");
                        try {
                            JSONArray subCategoryArray = response.getJSONArray("subcategory");
                            //new variables
                            SubCategory subCategory;
                            //RecyclerView rvProduct = null;
                            List<SubCategory> subCategoryList = new ArrayList<>();
                            //RecyclerViewProductAdapter adapter = new RecyclerViewProductAdapter(productList);


                            for(int i = 0; i < subCategoryArray.length(); i ++) {
                                Log.d(TAG, "onResponse: response length: " + response.length());

                                JSONObject subCategoryArr = subCategoryArray.getJSONObject(i);

                                String scId = subCategoryArr.getString("scid");
                                String scName = subCategoryArr.getString("scname");
                                String scDescription = subCategoryArr.getString("scdiscription");
                                String scThumbImgUrl = subCategoryArr.getString("scimageurl");

                                subCategory =
                                        new SubCategory(scId, scName, scDescription, scThumbImgUrl);
                                subCategoryList.add(subCategory);
                                //add response
                                subCategoryListener.getSubCategories(subCategoryList);
                                //rvProduct.setAdapter(adapter);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d(TAG, "onResponse: started");
                            //Toast.makeText(NetworkHelper.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        //dismissDialog();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(MainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        //dismissDialog();

                    }
                }
        );
        AppController.getInstance().addToRequestQueue(jsonObject);
    }

    @Override
    public void getProducts(final IDataManager.OnResponseProductListener productListener) {

        String productListJSON = "http://rjtmobile.com/ansari/shopingcart/androidapp/product_details.php?cid=107&scid=205&api_key=5fcb3d85f0ce5afb02618973b9e17919&user_id=1385";

        final JsonObjectRequest jsonObject = new JsonObjectRequest(
                Request.Method.GET,
                productListJSON,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse: started");
                        try {
                            JSONArray prodArray = response.getJSONArray("products");
                            //new variables
                            Product product;
                            //RecyclerView rvProduct = null;
                            List<Product> productList = new ArrayList<>();
                            //RecyclerViewProductAdapter adapter = new RecyclerViewProductAdapter(productList);


                            for(int i = 0; i < prodArray.length(); i ++) {
                                Log.d(TAG, "onResponse: response length: " + response.length());

                                JSONObject products = prodArray.getJSONObject(i);

                                String pId = products.getString("id");
                                String pName = products.getString("pname");
                                Log.d(TAG, "onResponse: ***name: " + pName);
                                String pQuantity = products.getString("quantity");
                                String pPrice = products.getString("prize");
                                String pDescription = products.getString("discription");
                                String pThumbImgUrl = products.getString("image");

                                product =
                                        new Product(pId, pName, pQuantity, pPrice, pDescription, pThumbImgUrl);
                                productList.add(product);
                                //add response
                                productListener.getProducts(productList);
                                //rvProduct.setAdapter(adapter);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d(TAG, "onResponse: started");
                            //Toast.makeText(NetworkHelper.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        //dismissDialog();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(MainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        //dismissDialog();

                    }
                }
        );
        AppController.getInstance().addToRequestQueue(jsonObject);


    }
}
