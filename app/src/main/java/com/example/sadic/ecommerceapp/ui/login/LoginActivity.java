package com.example.sadic.ecommerceapp.ui.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sadic.ecommerceapp.R;
import com.example.sadic.ecommerceapp.ui.mainfeed.MainActivity;
import com.example.sadic.ecommerceapp.utils.SharedPref;

public class LoginActivity extends AppCompatActivity implements IViewLogin {
    private static final String TAG = "LoginActivity";

    private EditText etMobile, etPassword;
    private TextView tvRegister;
    private Button btLogin;
    private IPresenterLogin presenterLogin;
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        SharedPref.init(this);
        etMobile.setText(SharedPref.read(SharedPref.MOBILE, null));
        etPassword.setText(SharedPref.read(SharedPref.PASSWORD, null));

        presenterLogin = new PresenterLogin(this);
        presenterLogin.getActivityData();


    }

    private void init() {
        etMobile = findViewById(R.id.etMobileLogin);
        etPassword = findViewById(R.id.etPassLogin);

        tvRegister = findViewById(R.id.tvRegister);

        btLogin = findViewById(R.id.btLogin);
    }

    @Override
    public void showLogin() {
        Log.d(TAG, "showLogin: started");
        SharedPref.init(this);
        SharedPref.read(SharedPref.MOBILE, null);
        SharedPref.read(SharedPref.PASSWORD, null);


    }

    @Override
    public void loginSuccessful() {
        Log.d(TAG, "loginSuccessful: started");
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);

    }

    public void eventHandler(View view) {
        Log.d(TAG, "eventHandler: started");
        switch (view.getId()) {
            case R.id.btLogin:
                presenterLogin.onLoginButtonClicked(etMobile.getText().toString(),
                        etPassword.getText().toString());
                break;
            case R.id.tvRegister:
                presenterLogin.onRegisterClicked();
                break;

        }
    }
}
