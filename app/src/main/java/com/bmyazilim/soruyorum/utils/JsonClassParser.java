package com.bmyazilim.soruyorum.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mertk on 12.12.2016.
 */
public class JsonClassParser {

    public void jsonArrayControl(JSONArray jsonArray){



    }

    public void jsonObjectControl(JSONObject jsonObject){

        try {
            String model = jsonObject.getString("model");



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public void usersParser(JSONArray jsonArray){




    }

}
