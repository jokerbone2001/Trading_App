package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

public class clickLargeImage extends AppCompatActivity {
    private ImageView enlargeView;
    private Bitmap imageBit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_large_image);
        enlargeView = findViewById(R.id.enlargeView);
        imageBit = (Bitmap) getIntent().getParcelableExtra("image");
        enlargeView.setImageBitmap(imageBit);
    }
}