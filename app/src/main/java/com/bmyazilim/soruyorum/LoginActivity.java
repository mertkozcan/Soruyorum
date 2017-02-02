package com.bmyazilim.soruyorum;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bmyazilim.soruyorum.models.Users;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.SignInButton;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import io.fabric.sdk.android.Fabric;


public class LoginActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "DlpF2zsN4tmcsOhIcHSzojAiV";
    private static final String TWITTER_SECRET = "oiGE1ZwbE88uRPuOj8iVuk51d2qxQksjMDNfrrDvtPNTFJLHXX";
    VolleyStuff volleyStuff;
    CallbackManager callbackManager;
    TwitterLoginButton btntwit;
    TwitterAuthClient authClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
//        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_login);
        volleyStuff=new VolleyStuff();

        final EditText txtmail=(EditText)findViewById(R.id.input_email);
        final EditText txtsifre=(EditText)findViewById(R.id.input_password);
        Button btngiris=(Button)findViewById(R.id.btn_login);
        LoginButton btnface=(LoginButton)findViewById(R.id.btn_face);
         btntwit=(TwitterLoginButton)findViewById(R.id.btn_twit);

        btnface.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday", "user_friends"));

        btngiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volleyStuff.loginControl(txtmail.getText().toString(), txtsifre.getText().toString(), new VolleyStuff.VolleyCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        //face login
        btnface.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.v("LoginActivity", response.toString());
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        //twitter login
        btntwit.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {

                Toast.makeText(getApplicationContext(),result.data.getUserName(),Toast.LENGTH_LONG).show();

            authClient.requestEmail(result.data, new Callback<String>() {
                @Override
                public void success(Result<String> result) {

                    Toast.makeText(getApplicationContext(),result.data,Toast.LENGTH_LONG).show();

                }

                @Override
                public void failure(TwitterException exception) {

                }
            });

            }

            @Override
            public void failure(TwitterException exception) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        btntwit.onActivityResult(requestCode, resultCode, data);
    }
}

