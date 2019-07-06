package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        callbackManager = CallbackManager.Factory.create();

        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        // If using in a fragment
        //loginButton.setFragment(this);


        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        //여기에서 application id와 user id얻으면 될듯!
        final String getUserId=Profile.getCurrentProfile().getId();
        final String getName=Profile.getCurrentProfile().getName();
        System.out.println(getName);
        System.out.println(getUserId);

        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });


        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // 성공하면 그 다음 tab activity 로 넘어가야함.
                        Intent mainIntent =new Intent(MainActivity.this, TabActivity.class);
                        Intent intentName =new Intent(MainActivity.this, TabActivity.class);
                        Intent intentID=new Intent(MainActivity.this, TabActivity.class);
                        intentName.putExtra("namedata",getName);
                        intentID.putExtra("IDdata",getUserId);


                        MainActivity.this.startActivity(mainIntent);
                        MainActivity.this.startActivity(intentID);
                        MainActivity.this.startActivity(intentName);




                        MainActivity.this.finish();
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

}

