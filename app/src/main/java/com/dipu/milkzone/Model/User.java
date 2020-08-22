package com.dipu.milkzone.Model;

public class User {
    public String id;
    public String uname;
    public String phone;
    public String email;
    public String password;
    public String imageID;

    public User() {
    }

    public User(String id, String uname, String phone, String email, String password, String imageID) {
        this.id = id;
        this.uname = uname;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.imageID = imageID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }
}
