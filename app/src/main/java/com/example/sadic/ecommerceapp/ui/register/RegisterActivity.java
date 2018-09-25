package com.example.sadic.ecommerceapp.ui.register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sadic.ecommerceapp.R;
import com.example.sadic.ecommerceapp.ui.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity implements IViewReg {
    private static final String TAG = "RegisterActivity";

    IPresenterReg presenterReg;
    EditText etFirstName, etLastName, etAddress, etPassword, etEmail, etMobile;
    Button btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();

        presenterReg = new PresenterRegister(this);
        presenterReg.getActivityData();
    }

    private void init() {
        etFirstName = findViewById(R.id.etFirstNameReg);
        etLastName = findViewById(R.id.etLastNameReg);
        etAddress = findViewById(R.id.etAddressReg);
        etPassword = findViewById(R.id.etPassReg);
        etEmail = findViewById(R.id.etEmailReg);
        etMobile = findViewById(R.id.etMobileReg);

        btRegister = findViewById(R.id.btRegister);
    }

    public void eventHandler(View view) {
        Log.d(TAG, "eventHandler: started");
        String fName = etFirstName.getText().toString();
        String lName = etLastName.getText().toString();
        String address = etAddress.getText().toString();
        String password = etPassword.getText().toString();
        String email = etEmail.getText().toString();
        String mobile = etMobile.getText().toString();

        presenterReg.onRegisterButtonClicked(fName, lName, address, email, mobile, password);
    }

    @Override
    public void showLogin() {

    }

    @Override
    public void loginSuccessful() {
        Log.d(TAG, "loginSuccessful: started");
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}
