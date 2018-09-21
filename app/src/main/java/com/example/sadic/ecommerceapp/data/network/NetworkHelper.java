package com.example.sadic.ecommerceapp.data.network;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
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
    public void getCategories(IDataManager.OnResponseNetworkListener categoryListener) {

    }

    @Override
    public void getSubCategories(IDataManager.OnResponseNetworkListener subCategoryListener) {

    }

    @Override
    public void getProducts(final IDataManager.OnResponseNetworkListener productListener) {

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
                            //RecyclerViewProduct adapter = new RecyclerViewProduct(productList);


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
