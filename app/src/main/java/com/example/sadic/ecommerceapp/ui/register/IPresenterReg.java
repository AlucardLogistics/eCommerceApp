package com.example.sadic.ecommerceapp.ui.register;

public interface IPresenterReg {

    void getActivityData();
    void onRegisterButtonClicked(String fName, String lName, String address,
                                  String email, String mobile, String password);

}
