package com.example.myapplication;

public class UserClass {
    private String name,loginID,password,email,securityQ,securityA;
    private int uniqueID;

    //default constructor
    public UserClass() {
    }

    //overloaded constructor
    public UserClass(int uniqueID, String name,String loginID,String password,String email,String securityQ,String securityA) {
        this.uniqueID = uniqueID;
        this.name = name;
        this.loginID=loginID;
        this.password=password;
        this.email=email;
        this.securityQ=securityQ;
        this.securityA=securityA;
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

    public void setUserID(int uniqueID) {
        this.uniqueID = uniqueID;
    }

    public void setSecurityQ(String securityQ) {
        this.securityQ = securityQ;
    }

    public void setSecurityA(String securityA) {
        this.securityA = securityA;
    }

    public String getName() {
        return name;
    }

    public int getUniqueID() {
        return uniqueID;
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

    public String getSecurityQ() {
        return securityQ;
    }

    public String getSecurityA() {
        return securityA;
    }
}
