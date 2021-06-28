package com.example.resume;

import java.io.Serializable;

public class profileJava implements Serializable {
    //variable
    public String name;
    public String mobile;
    public String email;
    public String address;


    //default constructor
    public profileJava() {
    }

    //constructor
    public profileJava(String name, String mobile, String email, String address)
    {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.address = address;

    }

    //setter and getter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "Resume{" +
                "name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
