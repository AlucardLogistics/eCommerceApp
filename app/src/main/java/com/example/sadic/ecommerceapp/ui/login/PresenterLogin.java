package com.example.sadic.ecommerceapp.ui.login;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.sadic.ecommerceapp.data.DataManager;
import com.example.sadic.ecommerceapp.data.IDataManager;
import com.example.sadic.ecommerceapp.data.network.model.User;
import com.example.sadic.ecommerceapp.ui.register.RegisterActivity;
import com.example.sadic.ecommerceapp.utils.SharedPref;

public class PresenterLogin implements IPresenterLogin, IDataManager.OnLoginListener {
    private static final String TAG = "PresenterLogin";

    IViewLogin iView;
    IDataManager dataManager;
    Context context;


    public PresenterLogin(LoginActivity loginActivity) {
        this.iView = loginActivity;
        this.dataManager = new DataManager(loginActivity);
        context = loginActivity.getApplicationContext();
    }

    @Override
    public void getActivityData() {

    }

    @Override
    public void onLoginButtonClicked(String mobile, String password) {
        Log.d(TAG, "onLoginButtonClicked: started");

        Log.d(TAG, "onLoginButtonClicked: sharedMobile: " + SharedPref.read(SharedPref.MOBILE, null));

        dataManager.login(this);

    }

    @Override
    public void onRegisterClicked() {
        Intent i = new Intent(context, RegisterActivity.class);
        context.startActivity(i);
    }

    @Override
    public void userLogin(String mobile, String password) {
        iView.loginSuccessful();
        iView.showLogin();
    }
}
