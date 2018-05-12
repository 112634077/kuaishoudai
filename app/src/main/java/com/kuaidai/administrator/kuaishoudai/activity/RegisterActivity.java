package com.kuaidai.administrator.kuaishoudai.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kuaidai.administrator.kuaishoudai.R;

//  <group android:checkableBehavior="single">

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView registerGetVerification;
    private EditText registerUser;
    private EditText registerVerification;
    private EditText registerPassword;
    private Button register_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerUser = (EditText) findViewById(R.id.register_user);
        registerVerification = findViewById(R.id.register_verification);

        registerPassword = (EditText) findViewById(R.id.register_password);
        registerPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        register_button = (Button) findViewById(R.id.register_button);
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }

    private void attemptLogin() {

        registerUser.setError(null);
        registerPassword.setError(null);
        registerVerification.setError(null);

        String user = registerUser.getText().toString();
        String password = registerPassword.getText().toString();
        String verification = registerVerification.getText().toString();

        boolean cancel = false;
        View focusView = null;
        // Check for a valid email address.
        if (TextUtils.isEmpty(user)) {
            registerUser.setError(getString(R.string.error_field_required));
            focusView = registerUser;
            cancel = true;
        }
        else if (!isAccountValid(user)) {
            registerUser.setError(getString(R.string.error_invalid_email));
            focusView = registerUser;
            cancel = true;
        }else if(TextUtils.isEmpty(verification)){
            registerVerification.setError(getString(R.string.error_incorrect_verification));
            focusView = registerVerification;
            cancel = true;
        }else if(TextUtils.isEmpty(password)){
            registerPassword.setError(getString(R.string.error_incorrect_required));
            focusView = registerPassword;
            cancel = true;
        }else if (!isPasswordValid(password)){
            registerPassword.setError(getString(R.string.error_invalid_password));
            focusView = registerPassword;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        }
    }
    //判断手机号码是否正确
    private boolean isAccountValid(String name){
        return name.length() == 11;
    }

    //密码长度不能少于八位数
    private boolean isPasswordValid(String password){
        return password.length() > 7;
    }

    @Override
    public void onClick(View v) {

    }
}
