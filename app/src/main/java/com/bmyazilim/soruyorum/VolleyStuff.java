package com.bmyazilim.soruyorum;


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.bmyazilim.soruyorum.utils.JsonClassParser;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by mertk on 12.12.2016.
 */
public class VolleyStuff  {

    JsonClassParser jsonClassParser;

    public VolleyStuff(){

        jsonClassParser=new JsonClassParser();

    }

    public void jsonObjectRequest(String url,String tag){

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        jsonClassParser.jsonObjectControl(response);

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag);

    }

    public void jsonArrayRequest(String url,String tag){

        JsonArrayRequest jsonArrReq = new JsonArrayRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                jsonClassParser.jsonArrayControl(response);

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonArrReq, tag);

    }

    public void stringRequest(String url,String tag){

        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag);

    }


}
