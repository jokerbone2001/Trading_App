package com.example.myapplication;

public class RequestClass {
    private int id;
    private String name;
    private String description;
    private String contact_info;
    private String userid;

    public RequestClass(){

    }

    public RequestClass(int id,String name,String description,String contact_info,String userid){
        this.id=id;
        this.name = name;
        this.description = description;
        this.contact_info = contact_info;
        this.userid=userid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContact_info() {
        return contact_info;
    }

    public void setContact_info(String contact_info) {
        this.contact_info = contact_info;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
