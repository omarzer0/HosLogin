package com.azapps.hoslogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static com.azapps.hoslogin.Constants.EMAIL_EXTRA;
import static com.azapps.hoslogin.Constants.MY_PREFS_NAME;
import static com.azapps.hoslogin.Constants.PASSWORD_EXTRA;

public class LoginActivity extends AppCompatActivity {
    private EditText emailEd, passwordEd;
    private Button loginBtn;
    private TextView signUpTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        iniViews();
        setListeners();
    }

    private void iniViews() {
        emailEd = findViewById(R.id.activity_login_ed_email_edit_text);
        passwordEd = findViewById(R.id.activity_login_ed_password_edit_text);
        loginBtn = findViewById(R.id.activity_login_btn_login_button);
        signUpTv = findViewById(R.id.activity_login_tv_sign_up_text_view);

    }

    private void setListeners() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEd.getText().toString();
                String password = passwordEd.getText().toString();

                if (email.equals("") || password.equals("")) {
                    Toast.makeText(LoginActivity.this, "please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    getFromPreference(email, password);
                }
            }
        });

        signUpTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                finish();
            }
        });
    }

    private void getFromPreference(String email, String password) {
        SharedPreferences preferences = getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        String savedEmail = preferences.getString(EMAIL_EXTRA, "");
        String savedPassword = preferences.getString(PASSWORD_EXTRA, "");
        if (savedEmail.equals(email) && savedPassword.equals(password)) {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        } else {
            Toast.makeText(this, "email or password are not correct!", Toast.LENGTH_SHORT).show();
        }
    }
}