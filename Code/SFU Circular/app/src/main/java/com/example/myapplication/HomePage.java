package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomePage extends AppCompatActivity implements View.OnClickListener{
    TextView infoTxt;
    Button logoutBtn, requestBtn, listingBtn;
    String currUniqueID,currLoginID,currName,currEmail,currQuestion,currAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        infoTxt=findViewById(R.id.displayInfoTxt);
        logoutBtn=findViewById(R.id.logoutBtn);

        requestBtn = findViewById(R.id.Request);
        requestBtn.setOnClickListener(this);

        listingBtn = findViewById(R.id.btn_post_listing);
        listingBtn.setOnClickListener(this);

        /*
        getIntent().getStringExtra("key")
        the "key" is what connects the data from first Activity to this
        */
        currUniqueID=String.valueOf(getIntent().getIntExtra("UNIQUEID",-1));
        currLoginID=getIntent().getStringExtra("LOGINID");
        currName=getIntent().getStringExtra("NAME");
        currEmail=getIntent().getStringExtra("EMAIL");
        currQuestion = getIntent().getStringExtra("QUESTION");
        currAnswer = getIntent().getStringExtra("ANSWER");
        infoTxt.setText("Unique ID: "+currUniqueID
                +"\nLogin ID: "+currLoginID
                +"\nName: "+currName
                +"\nEmail: "+currEmail
                +"\nSecurity Question: "+currQuestion
                +"\nSecurity Answer: "+currAnswer);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.logoutBtn:
                finish(); //closes current activity
                Intent intent = new Intent(HomePage.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent); //goes back to Login/register page (MainActivity)
                Toast.makeText(HomePage.this,"Logging out...",Toast.LENGTH_SHORT).show(); //pops a help msg for users
                break;
            case R.id.Request:
                Intent intent1 = new Intent(HomePage.this,RequestPage.class);
                startActivity(intent1);
                break;
            case R.id.btn_post_listing:
                startActivity(new Intent(HomePage.this, ListingPage.class));
                break;
        }
    }
}