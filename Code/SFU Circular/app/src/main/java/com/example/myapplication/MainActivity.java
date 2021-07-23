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
            registerPassConfirm, registerEmail, registerID, securityQ,securityA,forgotID,forgotEmail,forgotSecurityA,forgotPass,forgotPassConf;
    Button loginBtn, registerBtn,forgotBtn;
    TextView viewRegisterTxt, viewLoginTxt,viewForgotTxt,viewForgotQuestion,viewForgotBack;
    String grabUniqueID, grabName, grabLoginId, grabPass, grabPassConfirm, grabEmail,grabSecurityQ,grabSecurityA,grabForgotID,grabForgotEmail,grabForgotSecurityA,grabForgotPass,grabForgotPassConf;
    int forgotSteps=1;
    UserClass selectedUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //findViewById(R.id.'IDs set in .xml')

        idTxt = findViewById(R.id.eTextID);
        passwordTxt = findViewById(R.id.eTextPass);
        loginBtn = findViewById(R.id.btnLogin);
        viewRegisterTxt = findViewById(R.id.viewRegisterTxt);
        viewForgotTxt=findViewById(R.id.viewForgotPass);

        registerName = findViewById(R.id.editRegisterName);
        registerPass = findViewById(R.id.editRPassTxt);
        registerPassConfirm = findViewById(R.id.editRPassConfirmTxt);
        registerEmail = findViewById(R.id.editEmailTxt);
        registerID = findViewById(R.id.editIDTxt);
        registerBtn = findViewById(R.id.btnRegister);
        viewLoginTxt = findViewById(R.id.viewLoginTxt);
        securityQ = findViewById(R.id.editSecurityQ);
        securityA = findViewById(R.id.editSecurityA);

        forgotID = findViewById(R.id.forgotId);
        forgotEmail = findViewById(R.id.forgotEmail);
        forgotSecurityA = findViewById(R.id.forgotAnswer);
        forgotPass = findViewById(R.id.forgotPass);
        forgotPassConf = findViewById(R.id.forgotPassConfirm);
        viewForgotQuestion = findViewById(R.id.txtQuestion);
        forgotBtn = findViewById(R.id.btnForgot);
        viewForgotBack = findViewById(R.id.btnForgotBack);
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
        securityQ.setVisibility(View.VISIBLE);
        securityA.setVisibility(View.VISIBLE);

        //make login textbox disappear
        idTxt.setVisibility(View.GONE);
        passwordTxt.setVisibility(View.GONE);
        loginBtn.setVisibility(View.GONE);
        viewRegisterTxt.setVisibility(View.GONE);
        viewForgotTxt.setVisibility(View.GONE);
    }

    private void loginBoxAppear() {
        //make login textbox appear
        idTxt.setVisibility(View.VISIBLE);
        passwordTxt.setVisibility(View.VISIBLE);
        loginBtn.setVisibility(View.VISIBLE);
        viewRegisterTxt.setVisibility(View.VISIBLE);
        viewForgotTxt.setVisibility(View.VISIBLE);

        //make register textbox disappear
        registerName.setVisibility(View.GONE);
        registerPass.setVisibility(View.GONE);
        registerPassConfirm.setVisibility(View.GONE);
        registerEmail.setVisibility(View.GONE);
        registerID.setVisibility(View.GONE);
        registerBtn.setVisibility(View.GONE);
        viewLoginTxt.setVisibility(View.GONE);
        securityQ.setVisibility(View.GONE);
        securityA.setVisibility(View.GONE);

        //make forgot textbox disappear
        forgotID.setVisibility(View.GONE);
        forgotEmail.setVisibility(View.GONE);
        viewForgotQuestion.setVisibility(View.GONE);
        forgotSecurityA.setVisibility(View.GONE);
        forgotPass.setVisibility(View.GONE);
        forgotPassConf.setVisibility(View.GONE);
        forgotBtn.setVisibility(View.GONE);
        viewForgotBack.setVisibility(View.GONE);
    }

    private void forgotPassAppear(int step){
        //make login textbox disappear
        idTxt.setVisibility(View.GONE);
        passwordTxt.setVisibility(View.GONE);
        loginBtn.setVisibility(View.GONE);
        viewRegisterTxt.setVisibility(View.GONE);
        viewForgotTxt.setVisibility(View.GONE);

        //basically to refresh screen
        forgotID.setVisibility(View.GONE);
        forgotEmail.setVisibility(View.GONE);
        viewForgotQuestion.setVisibility(View.GONE);
        forgotSecurityA.setVisibility(View.GONE);
        forgotPass.setVisibility(View.GONE);
        forgotPassConf.setVisibility(View.GONE);
        switch(step){
            case 1: //show ID + Email
                forgotID.setVisibility(View.VISIBLE);
                forgotEmail.setVisibility(View.VISIBLE);
                forgotBtn.setText("Next");
                break;
            case 2: //show security questions + answer
                viewForgotQuestion.setVisibility(View.VISIBLE);
                forgotSecurityA.setVisibility(View.VISIBLE);
                forgotBtn.setText("Next");
                break;
            case 3: //show password related
                forgotPass.setVisibility(View.VISIBLE);
                forgotPassConf.setVisibility(View.VISIBLE);
                forgotBtn.setText("Confirm");
                break;
        }
        //make forget pass textboxes appear
        forgotBtn.setVisibility(View.VISIBLE);
        viewForgotBack.setVisibility(View.VISIBLE);
    }
    private int getStepProcess(){
        //specifically for forget password function
        //keep track which state it's in, depending on which state the app will show what
        return forgotSteps;
    }

    private void setStepProcess(int num){
        //specifically for forget password function
        forgotSteps=num;
    }

    private UserClass getSelectedUser(){
        //function for forgot password
        return selectedUser;
    }
    private void setSelectedUser(UserClass s){
        //function for forgot password
        selectedUser=s;
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
        //a temp user for forgot password function
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
                        intent.putExtra("QUESTION",x.getSecurityQ());
                        intent.putExtra("ANSWER",x.getSecurityA());
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
                grabSecurityQ = securityQ.getText().toString().trim();
                grabSecurityA = securityA.getText().toString().trim();
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
                    if(grabSecurityQ.isEmpty()){
                        securityQ.setError("Enter custom security question");
                        securityQ.requestFocus();
                        return;
                    }
                    if(grabSecurityA.isEmpty()){
                        securityA.setError("Enter security answer");
                        securityA.requestFocus();
                        return;
                    }

                    //above if statements are to check editTexts
                    //Implement code to check existing users so it doesn't create duplicates
                    //...
                    //loops through list of users to check duplicate
                    for (UserClass x : usersList) {
                        if (x.getLoginID().equals(grabLoginId)) {
                            //checks info entered compared to list
                            //if dupe exists then boolean check is false
                            noDupe = false;
                            break;
                        }
                    }
                    //sets the userClass with         -1 Name,    loginID,    password, email
                    userClass = new UserClass(-1, grabName, grabLoginId, grabPass, grabEmail,grabSecurityQ,grabSecurityA);
                    //...
                } catch (Exception e) {
                    //error catching
                    userClass = new UserClass(-1, "Placeholder Error Name", "Placeholder Error Login",
                            "Placeholder Error Pass", "Placeholder Error Email","Custom Sec. Q","Custom Sec.A");
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
                    securityQ.setText("");
                    securityA.setText("");
                    //makes login options appear
                    loginBoxAppear();
                } else {
                    //if dupe then pop a helpful message
                    Toast.makeText(MainActivity.this, "Account already exists", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.viewLoginTxt:
            case R.id.btnForgotBack:
                //makes login options appear
                loginBoxAppear();
                break;
            case R.id.viewRegisterTxt:
                //makes register options appear
                registerBoxAppear();
                break;
            case R.id.viewForgotPass:
                forgotPassAppear(forgotSteps); //always 1 here, to show 1st step of password recovery
                break;
            case R.id.btnForgot:
                boolean noAcc = true;
                switch (getStepProcess()){
                    case 1: // enter ID and Email, then press button to check
                        grabForgotID = forgotID.getText().toString().trim();
                        grabForgotEmail = forgotEmail.getText().toString().trim();
                        if(!loginIDRequirement(grabForgotID)){ //check id requirements
                            forgotID.setError("Enter a valid ID");
                            forgotID.requestFocus();
                            break;
                        }
                        if(!emailRequirement(grabForgotEmail)){ // check email requirements
                            forgotEmail.setError("Enter a valid email");
                            forgotEmail.requestFocus();
                            break;
                        }
                        selectedUser = new UserClass();
                        for(UserClass x: usersList){
                            if(grabForgotID.equals(x.getLoginID())&&grabForgotEmail.equals(x.getEmail())){
                                //if user enters a valid ID + Email matches that ID then
                                //move to phase 2
                                setStepProcess(2);
                                noAcc=false; // boolean to check if acc exists
                                setSelectedUser(x); //setter for temp selected user
                                //make phase 1 items disappear & make phase 2 appear
                                viewForgotQuestion.setText("Security Question: "+getSelectedUser().getSecurityQ()); //sets text for next screen
                                //ID + Pass disappear, Security question + answer appears
                                forgotPassAppear(getStepProcess());

                            }
                        }
                        if(noAcc){ //if no acc registered found
                            Toast.makeText(MainActivity.this, "No account found", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 2:
                        grabForgotSecurityA = forgotSecurityA.getText().toString().trim();
                        if(grabForgotSecurityA.equals(getSelectedUser().getSecurityA())){
                            //move to phase 3
                            setStepProcess(3);
                            //make phase 2 items disappear & make phase 3 appear
                            forgotPassAppear(getStepProcess());
                        }else{
                            Toast.makeText(MainActivity.this, "Wrong answer", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 3:
                        grabForgotPass = forgotPass.getText().toString().trim();
                        grabForgotPassConf = forgotPassConf.getText().toString().trim();
                        if (!passRequirement(grabForgotPass, grabForgotPassConf)) {
                            //check requirements
                            forgotPass.setError("Requirements: 1 Upper, 1 Lower, 1 Digit, Minimum 8 characters");
                            forgotPass.requestFocus();
                            return;
                        } else if (!passMatch(grabForgotPass, grabForgotPassConf)) {
                            forgotPassConf.setError("Passwords does not match");
                            forgotPassConf.requestFocus();
                            return;
                        }

                        //SET new password for user in memory
                        getSelectedUser().setPassword(grabForgotPass);
                        //ACTUALLY passing information to DB to update user password
                        boolean updated= dbHelper.updateUserPass(getSelectedUser());
                        if(updated){
                            setStepProcess(1); //back to beginning when new password is set
                            setSelectedUser(null); //clears temp selected user when done
                            loginBoxAppear();
                            Toast.makeText(MainActivity.this, "Successfully changed password", Toast.LENGTH_SHORT).show();
                            //clear all textboxes
                            forgotID.setText("");
                            forgotEmail.setText("");
                            forgotSecurityA.setText("");
                            forgotPass.setText("");
                            forgotPassConf.setText("");
                        }else {
                            Toast.makeText(MainActivity.this, "Failed to change password", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                break;
        }
    }
}