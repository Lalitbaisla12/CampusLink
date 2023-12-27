package com.example.campuslink.Models;

public class User {
    String uid;
    String name,email;
    public User(){

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNamed() {
        return name;
    }

    public void setNamed(String named) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{"+"uid-"+uid+'\''+",name='"+name +'\''+",email='" +email+'\''+'}';
    }
}
