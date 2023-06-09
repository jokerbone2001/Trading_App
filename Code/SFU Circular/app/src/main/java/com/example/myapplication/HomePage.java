package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomePage extends AppCompatActivity implements View.OnClickListener{
    TextView infoTxt;
    Button logoutBtn, requestBtn, listingBtn;
    Fragment fragmentRequest,fragmentListing;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        logoutBtn=findViewById(R.id.logoutBtn);

        requestBtn = findViewById(R.id.Request);
        requestBtn.setOnClickListener(this);

        listingBtn = findViewById(R.id.btn_post_listing);
        listingBtn.setOnClickListener(this);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        fragmentRequest = new FragmentRequest();
        fragmentListing = new FragmentListing();
        transaction.replace(R.id.fragmentDisplay,fragmentRequest);
        transaction.commit();

    }

    @Override
    public void onClick(View v) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch(v.getId()){
            case R.id.logoutBtn:
                finish(); //closes current activity
                Intent intent = new Intent(HomePage.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent); //goes back to Login/register page (MainActivity)
                Toast.makeText(HomePage.this,"Logging out...",Toast.LENGTH_SHORT).show(); //pops a help msg for users
                break;
            case R.id.Request:
//                Intent intent1 = new Intent(HomePage.this,RequestPage.class);
//                startActivity(intent1);
                transaction.replace(R.id.fragmentDisplay,fragmentRequest);
                transaction.commit();
                break;
            case R.id.btn_post_listing:
//                startActivity(new Intent(HomePage.this, ListingPage.class));
                transaction.replace(R.id.fragmentDisplay,fragmentListing);
                transaction.commit();
                break;
        }
    }
}