package com.example.myapplication;

public class ListingClass {
    private int id;
    private String itemName;
    private String description;
    private String phone;
    private String email;

    public ListingClass(){
    }

    public ListingClass(int id, String itemName, String description, String phone,String email){
        this.id = id;
        this.itemName = itemName;
        this.description = description;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
