package com.example.prateek.people.Contact;

/**
 * Created by prateek on 13/5/16.
 */
public class Person {


    private String name;
    private String phone;
    private String path;

    public Person(String name,String phone, String path)
    {
        this.name=name;
        this.phone=phone;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getPath() { return  path;}

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPath(String path) {this.path = path;}

    @Override
    public String toString() {
        return super.toString();
    }
}
