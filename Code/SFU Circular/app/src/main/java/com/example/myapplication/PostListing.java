package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class PostListing extends AppCompatActivity {

    private EditText et_itemName, et_description, et_phone, et_email;
    private DBListingHelper ldbHelper;  //listing database helper

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_listing);

        ldbHelper = new DBListingHelper(this);

        et_itemName = findViewById(R.id.et_itemName_listing);
        et_description = findViewById(R.id.et_descrption_listing);
        et_phone = findViewById(R.id.et_phone_listing);
        et_email = findViewById(R.id.et_email_listing);

        Button btn_submit_listing = findViewById(R.id.btn_submit_listing);
        btn_submit_listing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String itemName = et_itemName.getText().toString();
                String description = et_description.getText().toString();
                String phone = et_phone.getText().toString();
                String email = et_email.getText().toString();

                if (itemName.isEmpty() || description.isEmpty() || phone.isEmpty() || email.isEmpty())
                    Toast.makeText(PostListing.this, "Please enter all fields!", Toast.LENGTH_SHORT).show();

                else {

                    ListingClass prevListing = ldbHelper.getLastListing();
                    int id = prevListing == null ? 0 : prevListing.getId() + 1;

                    ListingClass listing = new ListingClass(id, itemName, description, phone, email);

                    boolean flag = ldbHelper.add(listing);

                    if (flag) {
                        Toast.makeText(PostListing.this, "save success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        PostListing.this.setResult(RESULT_OK, intent);
                        PostListing.this.finish();
                    } else
                        Toast.makeText(PostListing.this, "save fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}