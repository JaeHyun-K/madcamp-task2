package com.example.myapplication;

public class Contactlist {

    private String name;
    private String number;
    private String email;


    public Contactlist() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String phone) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhoneChanged(){
        return number.replace("-","");
    }
    @Override
    public String toString() {
        return this.number;
    }

    @Override
    public int hashCode() {
        return getPhoneChanged().hashCode();
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (obj instanceof RecyclerItem) {
//            return getPhoneChanged().equals(((RecyclerItem)obj).getPhoneChanged());
//        }
//        return false;
//    }
    }