package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText idTxt, passwordTxt, registerName, registerPass,
            registerPassConfirm, registerEmail, registerID;
    Button loginBtn, registerBtn;
    TextView viewRegisterTxt, viewLoginTxt;
    String grabUniqueID, grabName, grabLoginId, grabPass, grabPassConfirm, grabEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //findViewById(R.id.'IDs set in .xml')

        idTxt = findViewById(R.id.eTextID);
        passwordTxt = findViewById(R.id.eTextPass);
        loginBtn = findViewById(R.id.btnLogin);
        viewRegisterTxt = findViewById(R.id.viewRegisterTxt);

        registerName = findViewById(R.id.editRegisterName);
        registerPass = findViewById(R.id.editRPassTxt);
        registerPassConfirm = findViewById(R.id.editRPassConfirmTxt);
        registerEmail = findViewById(R.id.editEmailTxt);
        registerID = findViewById(R.id.editIDTxt);
        registerBtn = findViewById(R.id.btnRegister);
        viewLoginTxt = findViewById(R.id.viewLoginTxt);

    }

    private void registerBoxAppear() {
        //make register textbox appear
        registerName.setVisibility(View.VISIBLE);
        registerPass.setVisibility(View.VISIBLE);
        registerPassConfirm.setVisibility(View.VISIBLE);
        registerEmail.setVisibility(View.VISIBLE);
        registerID.setVisibility(View.VISIBLE);
        registerBtn.setVisibility(View.VISIBLE);
        viewLoginTxt.setVisibility(View.VISIBLE);

        //make login textbox disappear
        idTxt.setVisibility(View.GONE);
        passwordTxt.setVisibility(View.GONE);
        loginBtn.setVisibility(View.GONE);
        viewRegisterTxt.setVisibility(View.GONE);

    }

    private void loginBoxAppear() {
        //make login textbox appear
        idTxt.setVisibility(View.VISIBLE);
        passwordTxt.setVisibility(View.VISIBLE);
        loginBtn.setVisibility(View.VISIBLE);
        viewRegisterTxt.setVisibility(View.VISIBLE);

        //make register textbox disappear
        registerName.setVisibility(View.GONE);
        registerPass.setVisibility(View.GONE);
        registerPassConfirm.setVisibility(View.GONE);
        registerEmail.setVisibility(View.GONE);
        registerID.setVisibility(View.GONE);
        registerBtn.setVisibility(View.GONE);
        viewLoginTxt.setVisibility(View.GONE);
    }

    private boolean emailRequirement(String email) {
        if (!email.contains("@") || !email.contains(".")) {
            return false;
        }
        return true;
    }

    private boolean passRequirement(String e1, String e2) {
        boolean hasDigit = false, hasCapital = false, hasLower = false;
        if (e1.length() < 7) { //minimum 8 characters
            return false;
        }
        if (e1.length() != e2.length()) { // password matches length of password confirmation
            return false;
        }
        for (int i = 0; i < e1.length(); i++) {
            char ch = e1.charAt(i);
            if (Character.isDigit(ch)) { //has a digit
                hasDigit = true;
            } else if (Character.isUpperCase(ch)) { //has capital
                hasCapital = true;
            } else if (Character.isLowerCase(ch)) { //has lower
                hasLower = true;
            }
        }
        if (!hasDigit || !hasCapital || !hasLower) { //failed to meet requirement of for-loop
            return false;
        }
        return true; // meets all requirements
    }

    private boolean passMatch(String e1, String e2) {
        boolean matches = false;
        for (int i = 0; i < e1.length(); i++) {
            if (e1.charAt(i) == e2.charAt(i)) {
                matches = true;
            }
        }
        //failed to meet requirement of for-loop
        return matches;
    }

    private boolean loginIDRequirement(String ID) {
        if (ID.length() != 9) { // has to be 9 characters long
            return false;
        }
        for (int i = 0; i < ID.length(); i++) {
            char ch = ID.charAt(i);
            if (!Character.isDigit(ch)) { // check if there are any letters in ID (number only)
                return false;
            }
        }
        return true; //if is 9 characters & digit only
    }

    @Override
    public void onClick(View v) {
        //DBUserHelper class, see DBUserHelper for more detail
        DBUserHelper dbHelper = new DBUserHelper(MainActivity.this);
        //Creating a UserClass variable called userClass, see UserClass class for more detail
        UserClass userClass;
        // grabs all existing user info from user database - See DBUserHelper class for more info
        List<UserClass> usersList = dbHelper.getUserInfo();
        switch (v.getId()) {
            case R.id.btnLogin: // actions executed when clicked on the "login button"
                grabLoginId = idTxt.getText().toString().trim();
                grabPass = passwordTxt.getText().toString().trim();
                if (grabLoginId.isEmpty()) { // checks if idTxt is empty
                    //setError pops a warning msg at the error location
                    idTxt.setError("Field required");
                    idTxt.requestFocus();  //focuses cursor at error location
                    return;
                }
                if (grabPass.isEmpty()) { //checks if passwordTxt is empty
                    passwordTxt.setError("Field required");
                    passwordTxt.requestFocus();
                    return;
                }
                boolean errorToast = true;
                //loops through userList to find if there are such registered user info.
                for (UserClass x : usersList) {
                    if (x.getLoginID().equals(grabLoginId) && x.getPassword().equals(grabPass)) {
                        //save userinfomation
                        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putInt("userid",x.getUniqueID());
                        editor.putString("username",x.getLoginID());
                        editor.commit();

                        //login go to next activity
                        finish(); //closes current activity
                        // from MainActivity screen to HomePage screen
                        Intent intent = new Intent(MainActivity.this, HomePage.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        /*
                        .putExtra("key",data(int,String,etc...)
                        essentially passing data from one screen to the next screen
                        see HomePage.java under "onCreate" for more info
                         */
                        intent.putExtra("UNIQUEID", x.getUniqueID());
                        intent.putExtra("LOGINID", x.getLoginID());
                        intent.putExtra("NAME", x.getName());
                        intent.putExtra("EMAIL", x.getEmail());
                        startActivity(intent); // starts the next screen
                        // pops a helpful msg
                        Toast.makeText(MainActivity.this, "Logging in...", Toast.LENGTH_SHORT).show();
                        errorToast = false;
                    }
                }
                if (errorToast) { // if no such user exists from provided info then pop useful msg
                    Toast.makeText(MainActivity.this, "Either user doesn't exist or info entered is wrong", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnRegister:// actions executed when clicked on the "register button"
                boolean noDupe = true;
                //grabs the string for user and password
                grabName = registerName.getText().toString().trim();
                grabLoginId = registerID.getText().toString().trim();
                grabPass = registerPass.getText().toString().trim();
                grabPassConfirm = registerPassConfirm.getText().toString().trim();
                grabEmail = registerEmail.getText().toString().trim();
                try {

                    if (grabName.isEmpty()) {
                        registerName.setError("Enter your name");
                        registerName.requestFocus();
                        return;
                    }
                    if (!loginIDRequirement(grabLoginId)) {
                        registerID.setError("Requirements: 9 digits");
                        registerID.requestFocus();
                        return;
                    }
                    if (!passRequirement(grabPass, grabPassConfirm)) {
                        registerPass.setError("Requirements: 1 Upper, 1 Lower, 1 Digit, Minimum 8 characters");
                        registerPass.requestFocus();
                        return;
                    } else if (!passMatch(grabPass, grabPassConfirm)) {
                        registerPassConfirm.setError("Passwords does not match");
                        registerPassConfirm.requestFocus();
                        return;
                    }
                    if (!emailRequirement(grabEmail)) {
                        registerEmail.setError("Enter proper email, must contain '@' and '.'");
                        registerEmail.requestFocus();
                        return;
                    }
                    //above if statements are to check editTexts
                    //Implement code to check existing users so it doesn't create duplicates
                    //...
                    //loops through list of users to check duplicate
                    for (UserClass x : usersList) {
                        //debug purposes
//                        System.out.println("unique ID "+x.getUniqueID());
//                        System.out.println("name ID "+x.getName());
//                        System.out.println("login ID "+x.getLoginID());
//                        System.out.println("pass ID "+x.getPassword());
//                        System.out.println("email ID "+x.getEmail());
                        if (x.getLoginID().equals(grabLoginId)) {
                            //checks info entered compared to list
                            //if dupe exists then boolean check is false
                            noDupe = false;
                            break;
                        }
                    }
                    //sets the userClass with         -1 Name,    loginID,    password, email
                    userClass = new UserClass(-1, grabName, grabLoginId, grabPass, grabEmail);
                    //...
                } catch (Exception e) {
                    //error catching
                    userClass = new UserClass(-1, "Placeholder Error Name", "Placeholder Error Login", "Placeholder Error Pass", "Placeholder Error Email");
                    Toast.makeText(MainActivity.this, "User NOT Created", Toast.LENGTH_SHORT).show();
                }
                if (noDupe) {
                    // if noDupe is true then add user to 'User Database'
                    dbHelper.add(userClass);
                    //pops a little message at bottom of screen to let user know it has been created
                    Toast.makeText(MainActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                    registerName.setText("");
                    registerID.setText("");
                    registerPass.setText("");
                    registerPassConfirm.setText("");
                    registerEmail.setText("");
                    //makes login options appear
                    loginBoxAppear();
                } else {
                    //if dupe then pop a helpful message
                    Toast.makeText(MainActivity.this, "Account already exists", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.viewLoginTxt:
                //makes login options appear
                loginBoxAppear();
                break;
            case R.id.viewRegisterTxt:
                //makes register options appear
                registerBoxAppear();
                break;
        }
    }
}