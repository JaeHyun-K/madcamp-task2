package com.example.myapplication;

public class Contactlist {


    private String name;
    private String number;
    private int resId;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String name) {
        this.email = email;
    }

    public int getResId(){
        return resId;
    }

    public String getNumber() {
        return number;
    }

    public void setResId(int resId){
        this.resId=resId;
    }

    public void setNumber(String number) {
        this.number = number;
    }



}
