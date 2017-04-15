package com.bmyazilim.soruyorum.utils;


import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.bmyazilim.soruyorum.VolleyStuff;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.File;
import java.io.FileNotFoundException;

import cz.msebera.android.httpclient.Header;

/**
 * Created by mertk on 4.03.2017.
 */

public class Helper {


    public static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public static void uploadImage(String path, final VolleyStuff.VolleyCallback callback){

        String url="http://soruyorum.bmyazilim.net/kontrol.php?islem=resimyukle";
        File myFile = new File(path);
        RequestParams params = new RequestParams();
        try {
            params.put("profile_picture", myFile);
        } catch(FileNotFoundException e) {

            Log.e("Filenotfound",e.toString());
        }

// send request
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(url, params, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String s) {
               Log.i("basarili","basarili upload");

                callback.onSuccess(s.replace("success",""));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String s, Throwable throwable) {
                Log.i("basarisiz","basarisiz upload");
            }
        });

    }

    public static String getRealPathFromURI(Uri contentURI,Context context) {
        String result;
        Cursor cursor = context.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }


}
