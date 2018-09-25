package com.example.sadic.ecommerceapp.ui.register;

import android.content.Context;
import android.util.Log;

import com.example.sadic.ecommerceapp.data.DataManager;
import com.example.sadic.ecommerceapp.data.IDataManager;
import com.example.sadic.ecommerceapp.data.network.model.User;
import com.example.sadic.ecommerceapp.utils.SharedPref;

public class PresenterRegister implements IPresenterReg, IDataManager.OnRegisterListener {
    private static final String TAG = "PresenterRegister";

    IViewReg iView;
    IDataManager dataManager;
    Context context;

    public PresenterRegister(RegisterActivity registerActivity) {
        this.iView = registerActivity;
        this.dataManager = new DataManager(registerActivity);
        context = registerActivity.getApplicationContext();
    }

    @Override
    public void getActivityData() {

    }

    @Override
    public void onRegisterButtonClicked(String fName, String lName, String address,
                                        String email, String mobile, String password) {
        Log.d(TAG, "onRegisterButtonClicked: started");
        SharedPref.init(context);
        SharedPref.write(SharedPref.FIRST_NAME, fName);
        SharedPref.write(SharedPref.LAST_NAME, lName);
        SharedPref.write(SharedPref.ADDRESS, address);
        SharedPref.write(SharedPref.MOBILE, mobile);
        SharedPref.write(SharedPref.EMAIL, email);
        SharedPref.write(SharedPref.PASSWORD, password);

        dataManager.register(this);

    }

    @Override
    public void userRegister(User userRegister) {
        Log.d(TAG, "userRegister: started");
        iView.loginSuccessful();
    }
}
