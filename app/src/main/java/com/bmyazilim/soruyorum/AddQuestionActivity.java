package com.bmyazilim.soruyorum;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.bmyazilim.soruyorum.utils.Helper;
import com.flurgle.camerakit.CameraListener;
import com.flurgle.camerakit.CameraView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.List;

import id.zelory.compressor.Compressor;

public class AddQuestionActivity extends AppCompatActivity {

    CheckBox checkOptions;
    LinearLayout addQuestionOptionsLayout;
    TextView textViewOptions;
    ImageView imgQuestion;
    EditText txtQuestion;
    Spinner spnCategory;
    Button btnQuestion;
    CameraView cameraView;
    Button btnCapture;
    VolleyStuff volleyStuff;
    private static final int CAMERA_REQUEST = 2608;
    Uri image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        volleyStuff=new VolleyStuff();
        checkOptions =(CheckBox)findViewById(R.id.checkOptions);
        addQuestionOptionsLayout =(LinearLayout)findViewById(R.id.AddQuestionOptionsLayout);
        textViewOptions=(TextView)findViewById(R.id.textViewOptions);
       // imgQuestion=(ImageView)findViewById(R.id.questionPicture);
        txtQuestion=(EditText)findViewById(R.id.input_question_text);
        spnCategory=(Spinner)findViewById(R.id.spinner);
        btnQuestion=(Button)findViewById(R.id.btn_add_question);
        cameraView=(CameraView)findViewById(R.id.camera);
        btnCapture=(Button)findViewById(R.id.btnCapture);

        volleyStuff.getCategories(new VolleyStuff.CategoryCallback() {
            @Override
            public void onSuccess(List<String> listNames, List<Integer> listIds) {

                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        getApplicationContext(), android.R.layout.simple_spinner_item, listNames);

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spnCategory.setAdapter(adapter);

            }

            @Override
            public void onSuccess(List<Integer> listIds) {

            }
        });

        checkOptions.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                    textViewOptions.setVisibility(View.VISIBLE);
                    addQuestionOptionsLayout.setVisibility(View.VISIBLE);
                }
                else{

                    textViewOptions.setVisibility(View.GONE);
                    addQuestionOptionsLayout.setVisibility(View.GONE);
                }
            }
        });

      /*  imgQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);

            }
        }); */


        cameraView.setCameraListener(new CameraListener() {
            @Override
            public void onPictureTaken(byte[] jpeg) {
                super.onPictureTaken(jpeg);

                Bitmap result = BitmapFactory.decodeByteArray(jpeg, 0, jpeg.length);
            }
        });

        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraView.captureImage();
                cameraView.stop();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {

            image=data.getData();

        }

    }

    @Override
    protected void onPause() {
       cameraView.stop();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraView.start();
    }
}
