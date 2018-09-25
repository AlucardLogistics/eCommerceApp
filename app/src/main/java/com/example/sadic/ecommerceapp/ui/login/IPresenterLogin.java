package com.example.sadic.ecommerceapp.ui.login;

public interface IPresenterLogin {

    void getActivityData();
    void onLoginButtonClicked(String mobile, String password);
    void onRegisterClicked();
}
