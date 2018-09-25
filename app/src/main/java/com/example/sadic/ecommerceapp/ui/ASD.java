package com.example.sadic.ecommerceapp.ui;

import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sadic.ecommerceapp.R;
import com.example.sadic.ecommerceapp.data.network.model.Product;
import com.example.sadic.ecommerceapp.ui.mainfeed.MainActivity;
import com.example.sadic.ecommerceapp.utils.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ASD {
    private static final String TAG = "ASD";
    String productListJSON = "http://rjtmobile.com/ansari/shopingcart/androidapp/product_details.php?cid=107&scid=205&api_key=5fcb3d85f0ce5afb02618973b9e17919&user_id=1385";
    ArrayList<Product> productList = new ArrayList<>();
    Product product;


//    private void makeJsonObjectRequest() {
//        Log.d(TAG, "makeJsonObjectRequest: Started");
//        //showDialog();
//        final JsonObjectRequest jsonObject = new JsonObjectRequest(
//                Request.Method.GET,
//                //productListJSON,
//                null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.d(TAG, "onResponse: started");
//                        try {
//                            JSONArray prodArray = response.getJSONArray("products");
//
//
//                            for(int i = 0; i < prodArray.length(); i ++) {
//                                Log.d(TAG, "onResponse: response length: " + response.length());
//
//                                JSONObject products = prodArray.getJSONObject(i);
//
//                                String pId = products.getString("id");
//                                String pName = products.getString("pname");
//                                Log.d(TAG, "onResponse: ***name: " + pName);
//                                String pQuantity = products.getString("quantity");
//                                String pPrice = products.getString("prize");
//                                String pDescription = products.getString("discription");
//                                String pThumbImgUrl = products.getString("image");
//
//                                //product =
//                                        new Product(pId, pName, pQuantity, pPrice, pDescription, pThumbImgUrl);
//                               // productList.add(product);
//                                recyclerView.setAdapter(adapter);
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            //Toast.makeText(MainActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                        //dismissDialog();
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        //Toast.makeText(MainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
//                        //dismissDialog();
//
//                    }
//                }
//        );
//        //AppController.getInstance().addToRequestQueue(jsonObject);
//
//    }



}
