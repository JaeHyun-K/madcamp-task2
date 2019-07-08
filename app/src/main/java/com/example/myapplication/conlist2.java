package com.example.myapplication;

public class conlist2 {

    private String text_todo;
    private String text_date;

    public String getText_todo() {
        return text_todo;
    }

    public void setText_todo(String text_todo) {
        this.text_todo = text_todo;
    }

    public String getText_date(){
        return text_date;
    }

    public void setText_date(String text_date) {
        this.text_date = text_date;
    }

    public conlist2(String text_todo, String text_date) {
        this.text_todo = text_todo;
        this.text_date = text_date;
    }
}

