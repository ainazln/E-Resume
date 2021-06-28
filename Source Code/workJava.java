package com.example.resume;

import java.io.Serializable;
import java.util.Arrays;

public class workJava implements Serializable {
    //variable
    public String internname;
    public String internperiod;
    public String[] internjob;

    //default constructor
    public workJava(){

    }

    //constructor
    public workJava(String internname, String internperiod, String[] internjob) {
        this.internname = internname;
        this.internperiod = internperiod;
        this.internjob = new String[4];
        this.internjob[0] = internjob[0];
        this.internjob[1] = internjob[1];
        this.internjob[2] = internjob[2];
        this.internjob[3] = internjob[3];

    }

    //setter and getter
    public String getInternname() {
        return internname;
    }

    public void setInternname(String internname) {
        this.internname = internname;
    }

    public String getInternperiod() {
        return internperiod;
    }

    public void setInternperiod(String internperiod) {
        this.internperiod = internperiod;
    }

    public String[] getInternjob() {
        return internjob;
    }

    public void setInternjob(String[] internjob) {
        this.internjob = internjob;
    }

    @Override
    public String toString() {
        return "educationJava{" +
                "internname='" + internname + '\'' +
                ", internperiod='" + internperiod + '\'' +
                ", internjob=" + Arrays.toString(internjob) +
                '}';
    }
}
