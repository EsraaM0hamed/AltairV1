package com.altair.Rybari.login.view;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.altair.Rybari.R;
import com.altair.Rybari.main.view.MainActivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {
    CallbackManager callbackManager;
    Button loginBtn, normalLogin;
    private static final String EMAIL = "email";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        callbackManager = CallbackManager.Factory.create();
        loginBtn = findViewById(R.id.custom_fb);
        normalLogin = findViewById(R.id.login_btn);

        // loginBtn.setReadPermissions(Arrays.asList(EMAIL));
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        callbackManager = CallbackManager.Factory.create();

        if (isLoggedIn) {
            System.out.println("ana aslan login ");
        }


        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                System.out.println("Tmaaaaaam");

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        normalLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(homeIntent);
            }
        });


    }

    public void onClick(View v) {
        if (v == loginBtn) {
            LoginManager.getInstance().logInWithReadPermissions(
                    this,
                    Arrays.asList("user_photos", "email", "user_birthday", "public_profile")
            );
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
