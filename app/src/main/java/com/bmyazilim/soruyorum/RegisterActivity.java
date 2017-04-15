package com.bmyazilim.soruyorum;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bmyazilim.soruyorum.utils.Helper;

/**
 * Created by mertk on 3.03.2017.
 */


public class RegisterActivity extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button btn_continue=(Button)findViewById(R.id.btn_continue);
        final EditText input_ad=(EditText)findViewById(R.id.input_ad);
        final EditText input_soyad=(EditText)findViewById(R.id.input_soyad);
        final EditText input_mail=(EditText)findViewById(R.id.input_kmail);


        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                VolleyStuff.checkMail(input_mail.getText().toString(), new VolleyStuff.VolleyCallback() {
                    @Override
                    public void onSuccess(String result) {
                        if(result.equals("YOK")){

                            if(Helper.isValidEmail(input_mail.getText())) {


                                Intent i = new Intent(getApplicationContext(), UserRegisterActivity.class);

                                i.putExtra("ad", input_ad.getText().toString());
                                i.putExtra("soyad", input_soyad.getText().toString());
                                i.putExtra("mail", input_mail.getText().toString());


                                startActivity(i);

                            }
                            else{

                                Toast.makeText(getApplicationContext(),"Geçerli bir mail adresi giriniz.",Toast.LENGTH_LONG).show();
                            }

                        }

                        else{

                            Toast.makeText(getApplicationContext(),"Mail adresi kullanılıyor.",Toast.LENGTH_LONG).show();

                        }
                    }
                });



            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }
}
