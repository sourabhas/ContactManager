package com.soushetty.contactmanager.Model;

import android.util.Log;

/* the Model class which defines  the "Contact" table variables name and
constructors to assign them,getter and setter methods*/
public class Contacts {
    private int id;
    private String name;
    private String phone_number;
   //constructors
    public Contacts(){

    }

    public Contacts(String name,String phone_number){
        this.name =name;
        this.phone_number = phone_number;

    }

    public Contacts(int id, String name, String phone_number) {
        this.id = id;
        this.name =name;
        this.phone_number = phone_number;
    }

    //getters and setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        //Log.d("getname","name is :"+name);
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
