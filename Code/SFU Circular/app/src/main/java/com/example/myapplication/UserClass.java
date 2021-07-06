package com.example.myapplication;

public class UserClass {
    private String name,loginID,password,email;
    private int userID;

    //default constructor
    public UserClass() {
    }

    //overloaded constructor
    public UserClass(int userID, String name,String loginID,String password,String email ) {
        this.userID = userID;
        this.name = name;
        this.loginID=loginID;
        this.password=password;
        this.email=email;

    }

    //setters and getters
    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public int getUserID() {
        return userID;
    }

    public String getLoginID() {
        return loginID;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
