package com.bmyazilim.soruyorum;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bmyazilim.soruyorum.models.Users;
import com.bmyazilim.soruyorum.utils.Helper;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.InputStream;

import retrofit2.http.Path;

/**
 * Created by mertk on 3.03.2017.
 */

public class UserRegisterActivity extends AppCompatActivity {

    ImageView input_picture;
    VolleyStuff volleyStuff;
    Users model;
    Uri image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userregister);

        final Bundle bundle=getIntent().getExtras();
        volleyStuff=new VolleyStuff();
        Button btn_register=(Button)findViewById(R.id.btn_register);
     final   EditText input_username=(EditText)findViewById(R.id.input_username);
     final   EditText input_ksifre=(EditText)findViewById(R.id.input_ksifre);
        final EditText input_ksifretekrar=(EditText)findViewById(R.id.input_ksifretekrar);
         input_picture=(ImageView)findViewById(R.id.input_picture);



        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                VolleyStuff.checkUsername(input_username.getText().toString(), new VolleyStuff.VolleyCallback() {
                    @Override
                    public void onSuccess(String result) {

                        if(result.equals("YOK")){

                                Helper.uploadImage(Helper.getRealPathFromURI(image, getApplicationContext()), new VolleyStuff.VolleyCallback() {
                                    @Override
                                    public void onSuccess(String result) {

                                        model = new Users(0, input_username.getText().toString(), bundle.getString("ad").replaceAll(" ", "%20"), bundle.getString("soyad").replaceAll(" ", "%20"),
                                                input_ksifre.getText().toString(), bundle.getString("mail"), Integer.parseInt(result), 0, 0, 0, 0);

                                        volleyStuff.registerUser(model, new VolleyStuff.VolleyCallback() {
                                            @Override
                                            public void onSuccess(String result) {
                                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                                            }
                                        });

                                    }
                                });

                        }

                        else{

                            Toast.makeText(getApplicationContext(),"Kullanıcı adı alınmış.",Toast.LENGTH_LONG).show();

                        }

                    }
                });


            }
        });

        input_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 1);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                Toast.makeText(getApplicationContext(),"Hata oluştu. Lütfen tekrar deneyiniz.",Toast.LENGTH_LONG).show();
                return;
            }

             image=data.getData();

            Picasso.with(getApplicationContext()).load(image).resize(200,200).centerCrop().into(input_picture);
        }

    }
}
