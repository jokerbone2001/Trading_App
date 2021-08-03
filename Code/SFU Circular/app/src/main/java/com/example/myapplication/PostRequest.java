package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class PostRequest extends AppCompatActivity {
    private EditText et_name,et_description,et_contactinfo;
    private DBRequestHelper dbRequestHelper;
    private ImageView insertImage;
    private Bitmap imageBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_request);
        dbRequestHelper = new DBRequestHelper(PostRequest.this);
        et_name = findViewById(R.id.et_name);
        et_description = findViewById(R.id.et_description);
        et_contactinfo = findViewById(R.id.et_contactinfo);

        insertImage = findViewById(R.id.takeImage);

        //Request Camera permission
        cameraPerm();
        insertImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,100);
            }
        });

        Button submitBtn = findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_name.getText().toString();
                String description = et_description.getText().toString();
                String contactInfo = et_contactinfo.getText().toString();
                if(name.equals("")||description.equals("")||contactInfo.equals("")||imageBitmap == null){
                    Toast.makeText(PostRequest.this,"information is empty",Toast.LENGTH_SHORT).show();
                }else {
                    RequestClass requestClass = new RequestClass();
                    requestClass.setName(name);
                    requestClass.setDescription(description);
                    requestClass.setContact_info(contactInfo);
                    requestClass.setImage(imageBitmap);
                    SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
                    int userid=sp.getInt("userid",0);
                    requestClass.setUserid(userid+"");
                    boolean flag = dbRequestHelper.add(requestClass);
                    if(flag){
                        Toast.makeText(PostRequest.this,"save success",Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent();
                        PostRequest.this.setResult(RESULT_OK,intent);
                        PostRequest.this.finish();
                    }else{
                        Toast.makeText(PostRequest.this,"save fail",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void cameraPerm(){
        if(ContextCompat.checkSelfPermission(PostRequest.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(PostRequest.this,new String[]{
                    Manifest.permission.CAMERA
            },100);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(RESULT_OK == resultCode){
            Bitmap capImage =(Bitmap)data.getExtras().get("data");
            imageBitmap=capImage;
            insertImage.setImageBitmap(capImage);
        }
    }

}