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
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.*;
import android.widget.*;

public class PostListing extends AppCompatActivity {

    private EditText et_itemName, et_description, et_phone, et_email;
    private DBListingHelper ldbHelper;  //listing database helper
    private ImageView insertImage;
    private Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_listing);

        ldbHelper = new DBListingHelper(this);

        et_itemName = findViewById(R.id.et_itemName_listing);
        et_description = findViewById(R.id.et_descrption_listing);
        et_phone = findViewById(R.id.et_phone_listing);
        et_email = findViewById(R.id.et_email_listing);
        insertImage = findViewById(R.id.add_listing_image);

        //Request Camera permission
        cameraPerm();
        insertImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("TEST");
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,100);
            }
        });

        Button btn_submit_listing = findViewById(R.id.btn_submit_listing);
        btn_submit_listing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String itemName = et_itemName.getText().toString();
                String description = et_description.getText().toString();
                String phone = et_phone.getText().toString();
                String email = et_email.getText().toString();

                if (itemName.isEmpty() || description.isEmpty() || phone.isEmpty() || email.isEmpty() || image == null)
                    Toast.makeText(PostListing.this, "Please enter all fields!", Toast.LENGTH_SHORT).show();

                else {
                    ListingClass listing = new ListingClass(-1, itemName, description, phone, email, -1, image);

                    SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
                    listing.setId(sp.getInt("userid",0));

                    boolean flag = ldbHelper.add(listing);

                    if (flag) {
                        Toast.makeText(PostListing.this, "Save successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        PostListing.this.setResult(RESULT_OK, intent);
                        PostListing.this.finish();
                    } else
                        Toast.makeText(PostListing.this, "Save failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void cameraPerm(){
        if(ContextCompat.checkSelfPermission(PostListing.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(PostListing.this,new String[]{
                    Manifest.permission.CAMERA
            },100);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(RESULT_OK == resultCode){
            Bitmap capImage =(Bitmap)data.getExtras().get("data");
            image=capImage;
            insertImage.setImageBitmap(capImage);
        }
    }
}