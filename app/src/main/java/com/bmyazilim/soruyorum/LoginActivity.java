package com.bmyazilim.soruyorum;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.FacebookSdk;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;

public class LoginActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "DlpF2zsN4tmcsOhIcHSzojAiV";
    private static final String TWITTER_SECRET = "oiGE1ZwbE88uRPuOj8iVuk51d2qxQksjMDNfrrDvtPNTFJLHXX";
    VolleyStuff volleyStuff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_login);
        volleyStuff=new VolleyStuff();

        volleyStuff.jsonObjectRequest("http://soruyorum.bmyazilim.net/kontrol.php?islem=kullaniciliste","kullaniciliste");
    }
}
