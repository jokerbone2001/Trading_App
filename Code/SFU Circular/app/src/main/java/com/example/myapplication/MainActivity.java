package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView usernameTxt = findViewById(R.id.eTextName);
        TextView passwordTxt = findViewById(R.id.eTextPass);
        Button loginBtn = findViewById(R.id.btnLogin);
        Button registerBtn = findViewById(R.id.btnRegister);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Currently does nothing but just grabs the 2 EditText and prints them
                String grabUser = usernameTxt.getText().toString();
                String grabPass = passwordTxt.getText().toString();
                System.out.println("name: "+grabUser);
                System.out.println("name: "+grabPass);

                DBUserHelper dbHelper = new DBUserHelper(MainActivity.this);

            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserClass userClass; //Creating a UserClass variable called userClass, see UserClass class for more detail
                DBUserHelper dbHelper = new DBUserHelper(MainActivity.this); //DBUserHelper class, see DBUserHelper for more detail
                //grabs the string for user and password
                String grabUser = usernameTxt.getText().toString();
                String grabPass = passwordTxt.getText().toString();
                try{
                    //Implement code to check existing users so it doesn't create duplicates
                    //...




                    //...
                    //sets the userClass with -1 id, name, loginID, password, email
                    userClass = new UserClass(-1,"Placeholder Name",grabUser,grabPass,"Placeholder Email");
                    //pops a little message at bottom of screen to let user know it has been created
                    Toast.makeText(MainActivity.this,"User Created",Toast.LENGTH_SHORT).show();
                }catch(Exception e){
                    //error catching
                    userClass = new UserClass(-1,"Placeholder Error Name","Placeholder Error Login","Placeholder Error Pass","Placeholder Error Email");
                    Toast.makeText(MainActivity.this,"User NOT Created",Toast.LENGTH_SHORT).show();
                }
                boolean success = dbHelper.add(userClass);
                Toast.makeText(MainActivity.this,"Success = "+success,Toast.LENGTH_SHORT).show();
                usernameTxt.setText("");
                passwordTxt.setText("");
            }
        });
    }
}