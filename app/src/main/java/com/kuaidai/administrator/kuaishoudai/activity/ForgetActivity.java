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

public class ForgetActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView forgetGetVerification;
    private EditText forgetUser;
    private EditText forgetVerification;
    private EditText forgetPassword;
    private Button forget_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        forgetUser = (EditText) findViewById(R.id.forger_user);
        forgetVerification = findViewById(R.id.forger_verification);

        forgetPassword = (EditText) findViewById(R.id.forger_password);
        forgetPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        forget_button = (Button) findViewById(R.id.forger_button);
        forget_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }

    private void attemptLogin() {

        forgetUser.setError(null);
        forgetPassword.setError(null);
        forgetVerification.setError(null);

        String user = forgetUser.getText().toString();
        String password = forgetPassword.getText().toString();
        String verification = forgetVerification.getText().toString();

        boolean cancel = false;
        View focusView = null;
        // Check for a valid email address.
        if (TextUtils.isEmpty(user)) {
            forgetUser.setError(getString(R.string.error_field_required));
            focusView = forgetUser;
            cancel = true;
        }
        else if (!isAccountValid(user)) {
            forgetUser.setError(getString(R.string.error_invalid_email));
            focusView = forgetUser;
            cancel = true;
        }else if(TextUtils.isEmpty(verification)){
            forgetVerification.setError(getString(R.string.error_incorrect_verification));
            focusView = forgetVerification;
            cancel = true;
        }else if(TextUtils.isEmpty(password)){
            forgetPassword.setError(getString(R.string.error_incorrect_required));
            focusView = forgetPassword;
            cancel = true;
        }else if (!isPasswordValid(password)){
            forgetPassword.setError(getString(R.string.error_invalid_password));
            focusView = forgetPassword;
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
