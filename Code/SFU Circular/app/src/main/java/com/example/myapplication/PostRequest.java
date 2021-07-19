package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PostRequest extends AppCompatActivity {
    private EditText et_name,et_description,et_contactinfo;
    private Button submitBtn;
    private DBRequestHelper dbRequestHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_request);
        dbRequestHelper = new DBRequestHelper(PostRequest.this);
        et_name = findViewById(R.id.et_name);
        et_description = findViewById(R.id.et_description);
        et_contactinfo = findViewById(R.id.et_contactinfo);
        submitBtn = findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_name.getText().toString();
                String description = et_description.getText().toString();
                String contactInfo = et_contactinfo.getText().toString();
                if(name.equals("")||description.equals("")||contactInfo.equals("")){
                    Toast.makeText(PostRequest.this,"information is empty",Toast.LENGTH_SHORT).show();
                }else {
                    RequestClass requestClass = new RequestClass();
                    requestClass.setName(name);
                    requestClass.setDescription(description);
                    requestClass.setContact_info(contactInfo);
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
}