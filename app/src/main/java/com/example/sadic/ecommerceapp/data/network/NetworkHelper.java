package com.example.sadic.ecommerceapp.data.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.sadic.ecommerceapp.data.network.model.Category;
import com.example.sadic.ecommerceapp.data.network.model.SubCategory;
import com.example.sadic.ecommerceapp.data.network.model.User;
import com.example.sadic.ecommerceapp.utils.AppController;
import com.example.sadic.ecommerceapp.data.IDataManager;
import com.example.sadic.ecommerceapp.data.network.model.Product;
import com.example.sadic.ecommerceapp.utils.SharedPref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NetworkHelper implements INetworkHelper {
    private static final String TAG = "NetworkHelper";

    Context context;

    public NetworkHelper(Context context) {
        this.context = context;
        SharedPref.init(context);
    }

    @Override
    public void getCategories(final IDataManager.OnResponseCategoryListener categoryListener) {

        SharedPref.init(context);
        String id = SharedPref.read(SharedPref.ID, null);
        String api = SharedPref.read(SharedPref.API_KEY, null);

        String categoryListJSON = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_category.php?api_key="
                + api + "&user_id=" + id;

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
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                            alertDialog.setTitle("Connection time out.");
                            alertDialog.setMessage("Check your internet connection.");
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

        SharedPref.init(context);
        String id = SharedPref.read(SharedPref.ID, null);
        String api = SharedPref.read(SharedPref.API_KEY, null);

        String subCategoryListJSON = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_sub_category.php?Id=107&api_key="
                + api + "&user_id=" + id;

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
        Log.d(TAG, "getProducts: started");
        SharedPref.init(context);
        String id = SharedPref.read(SharedPref.ID, null);
        String api = SharedPref.read(SharedPref.API_KEY, null);
        Log.d(TAG, "getProducts: id + api " + id + " " + api);

        String productListJSONDynamic = "http://rjtmobile.com/ansari/shopingcart/androidapp/product_details.php?cid=107&scid=205&api_key="
                + api + "&user_id=" + id;
        //String productListJSON = "http://rjtmobile.com/ansari/shopingcart/androidapp/product_details.php?cid=107&scid=205&api_key=040a90722f3f26398ccd74b73cace4d3&user_id=1385";

        final JsonObjectRequest jsonObject = new JsonObjectRequest(
                Request.Method.GET,
                productListJSONDynamic,
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

                                //rvProduct.setAdapter(adapter);
                            }
                            //set data to listener
                            productListener.getProducts(productList);

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
    public void login(final IDataManager.OnLoginListener listenerLogin) {
        Log.d(TAG, "login: started");

        SharedPref.init(context);
        String mobile = SharedPref.read(SharedPref.MOBILE, null);
        String password = SharedPref.read(SharedPref.PASSWORD, null);
        Log.d(TAG, "login: mobile: " + SharedPref.read(SharedPref.MOBILE, null));


        String loginURL =  "http://rjtmobile.com/aamir/e-commerce/android-app/shop_login.php?mobile="
                + mobile + "&password=" + password;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                loginURL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response != null) {
                            //User userCredentials;
                            //User userReg = new User();
                            try {
                                JSONObject loginObj = response.getJSONObject(0);

                                //String messageSuccess = loginObj.getString("msg");
                                String id = loginObj.getString("id");
                                String firstName = loginObj.getString("firstname");
                                String lastName = loginObj.getString("lastname");
                                String email = loginObj.getString("email");
                                String mobile = loginObj.getString("mobile");
                                String apiKey = loginObj.getString("appapikey ");

                                String responseLogin = id + " " + firstName + " " + lastName
                                        + " " + email  + " " + mobile + " " + apiKey;

                                Log.d(TAG, "onResponse: responseLogin: " + responseLogin);

                                SharedPref.write(SharedPref.ID, id);
                                SharedPref.write(SharedPref.FIRST_NAME, firstName);
                                SharedPref.write(SharedPref.LAST_NAME, lastName);
                                SharedPref.write(SharedPref.EMAIL, email);
                                SharedPref.write(SharedPref.MOBILE, mobile);
                                SharedPref.write(SharedPref.API_KEY, apiKey);

                                String shareData = SharedPref.read(SharedPref.ID, null)
                                + SharedPref.read(SharedPref.FIRST_NAME, null)
                                        + SharedPref.read(SharedPref.LAST_NAME, null)
                                        + SharedPref.read(SharedPref.EMAIL, null)
                                        + SharedPref.read(SharedPref.MOBILE, null)
                                        + SharedPref.read(SharedPref.API_KEY, null);

                                Log.d(TAG, "onResponse: sharedPrefData: " + shareData);
                                Toast.makeText(context, "Welcome!", Toast.LENGTH_SHORT).show();
                                listenerLogin.userLogin(SharedPref.read(SharedPref.MOBILE, null),
                                        SharedPref.read(SharedPref.PASSWORD, null));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Mobile or Password is wrong!", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);

    }

    @Override
    public void register(final IDataManager.OnRegisterListener listenerRegister) {
        Log.d(TAG, "register: started");

        SharedPref.init(context);
        final String firstName = SharedPref.read(SharedPref.FIRST_NAME, null);
        final String lastName = SharedPref.read(SharedPref.LAST_NAME, null);
        final String address = SharedPref.read(SharedPref.ADDRESS, null);
        final String email = SharedPref.read(SharedPref.EMAIL, null);
        final String mobile = SharedPref.read(SharedPref.MOBILE, null);
        final String password = SharedPref.read(SharedPref.PASSWORD, null);

        Log.d(TAG, "register: addressTest: " + address);


        String urlReg = "http://rjtmobile.com/aamir/e-commerce/android-app/shop_reg.php?fname="
                + firstName + "&lname=" + lastName + "&address=" + address + "&email="
                + email + "&mobile=" + mobile + "&password=" + password;

        Log.d(TAG, "register: URLREG: " + urlReg);

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                urlReg,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "onResponse: started");
                        User user = new User(firstName, lastName, address, email, mobile, password);
                        listenerRegister.userRegister(user);
                        Log.d(TAG, "onResponse: userDATA: " + user.toString());
                        Toast.makeText(context, "User was Registered!", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "onErrorResponse: started");
                        Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        AppController.getInstance().addToRequestQueue(stringRequest);
    }
}
