package com.example.myapplication;

import android.graphics.Bitmap;

public class ListingClass {
    private int userId;
    private String itemName;
    private String description;
    private String phone;
    private String email;
    private int itemId;
    private Bitmap image;

    public ListingClass(){
    }

    public ListingClass(int userId, String itemName, String description, String phone,String email, int itemId, Bitmap image){
        this.userId = userId;
        this.itemName = itemName;
        this.description = description;
        this.phone = phone;
        this.email = email;
        this.image = image;
        this.itemId = itemId;
    }

    /**
     *
     * @return User ID
     */
    public int getId() {
        return userId;
    }

    /**
     *
     * @param id User ID
     */
    public void setId(int id) {
        this.userId = id;
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

    public void setItemId(int id){ itemId = id;}

    public int getItemId()
    {
        return itemId;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
