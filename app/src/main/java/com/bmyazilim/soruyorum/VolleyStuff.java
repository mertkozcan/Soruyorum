package com.bmyazilim.soruyorum;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.bmyazilim.soruyorum.models.Users;
import com.google.gson.JsonArray;
import com.twitter.sdk.android.core.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mertk on 12.12.2016.
 */
public class VolleyStuff  {

    public void loginControl(String mail,String password,final VolleyCallback callback){

        String tag = "giriskontrol";

        String url = "http://soruyorum.bmyazilim.net/kontrol.php?islem=giriskontrol&mail="+mail+"&password="+password;


        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag);

    }

    public void registerUser(Users model, final VolleyCallback callback){

        String tag="kisiekle";

        String url="http://soruyorum.bmyazilim.net/kontrol.php?islem=kisiekle&name="+model.userReelName+"&surname="+model.userSurname+"&mail="+model.userMail+
                "&username="+model.userName+"&password="+model.userPassword+"&pictureid="+model.profilePictureID;

        StringRequest stringRequest =new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(stringRequest,tag);

    }

    public static void checkMail(String mail, final VolleyCallback callback){

        String tag = "mailkontrol";

        String url = "http://soruyorum.bmyazilim.net/kontrol.php?islem=mailkontrol&mail="+mail;


        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });



// Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag);



    }

    public static void checkUsername(String username, final VolleyCallback callback){

        String tag = "usernamekontrol";

        String url = "http://soruyorum.bmyazilim.net/kontrol.php?islem=usernamekontrol&username="+username;


        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
              callback.onSuccess(response);

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag);


    }

    public void getProfileInfo(String mail, String password, final jsonUsersVolleyCallback callback){

        String tag = "profilbilgileri";

        String url = "http://soruyorum.bmyazilim.net/kontrol.php?islem=kisibilgigetir&mail="+mail+"&password="+password;

        JsonArrayRequest jsonRequest =new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Users model=null;
                try {
                     model =new Users(response.getJSONObject(0).getInt("userID"),response.getJSONObject(0).getString("userName"),response.getJSONObject(0).getString("userReelName"),
                            response.getJSONObject(0).getString("userSurname"),response.getJSONObject(0).getString("userPassword"),response.getJSONObject(0).getString("userMail"),response.getJSONObject(0).getInt("profilePictureID"),
                            response.getJSONObject(0).getInt("faceLogin"), response.getJSONObject(0).getInt("twitLogin"), response.getJSONObject(0).getInt("googleLogin"),response.getJSONObject(0).getInt("deleted"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                callback.onSuccess(model);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(jsonRequest, tag);

    }

    public  void getProfilePicture(int id, final VolleyCallback callback){

        String tag = "profilresmi";

        String url = "http://soruyorum.bmyazilim.net/kontrol.php?islem=profilresmigetir&id="+id;


        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });



// Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag);



    }

    public void getCategories(final CategoryCallback callback){

        String tag = "kategorigetir";

        String url = "http://soruyorum.bmyazilim.net/kontrol.php?islem=kategorigetir";

        JsonArrayRequest jsonArrayRequest =new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                List<String> listNames =new ArrayList<>();
                List<Integer> listIds =new ArrayList<>();

                for(int i =0;i<response.length();i++){

                    try {
                        listNames.add(response.getJSONObject(i).getString("CategoryName"));
                        listIds.add(response.getJSONObject(i).getInt("CategoryID"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                callback.onSuccess(listNames,listIds);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonArrayRequest, tag);

    }

    public void categoryFollow(int userId,int categoryId,final VolleyCallback callback){

        String tag="kategoritakip";

        String url="http://soruyorum.bmyazilim.net/kontrol.php?islem=kategoritakip&userid="+userId+"&categoryid="+categoryId;

        StringRequest stringRequest =new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(stringRequest,tag);

    }

    public void getCategoryFollow(int userId,final CategoryCallback callback){

        String tag = "kategoritakipgetir";

        String url = "http://soruyorum.bmyazilim.net/kontrol.php?islem=kategoritakipedilen&userid="+userId;

        JsonArrayRequest jsonArrayRequest =new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                List<Integer> listIds =new ArrayList<>();

                for(int i =0;i<response.length();i++){

                    try {

                        listIds.add(response.getJSONObject(i).getInt("CategoryID"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                callback.onSuccess(listIds);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonArrayRequest, tag);

    }

    public interface VolleyCallback{
        void onSuccess(String result);

    }

    public interface jsonUsersVolleyCallback{
        void onSuccess(Users model);

    }

    public interface CategoryCallback{

        void onSuccess(List<String> listNames,List<Integer> listIds);

        void onSuccess(List<Integer> listIds);
    }

}
