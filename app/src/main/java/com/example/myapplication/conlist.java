package com.example.myapplication;

public class conlist {

    private String name;
    private String number;
    private int resId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public conlist(String name, String number,int resId) {
        this.name = name;
        this.number = number;
        this.resId=resId;
    }
}
