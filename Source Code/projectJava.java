package com.example.resume;

import java.io.Serializable;
import java.util.Arrays;

public class projectJava implements Serializable {
    //variable
    public String[] sysdev;

    //default constructor
    public projectJava(){

    }

    //constructor
    public projectJava(String[] sysdev) {
        this.sysdev = new String[10];
        this.sysdev[0] = sysdev[0];
        this.sysdev[1] = sysdev[1];
        this.sysdev[2] = sysdev[2];
        this.sysdev[3] = sysdev[3];
        this.sysdev[4] = sysdev[4];
        this.sysdev[5] = sysdev[5];
        this.sysdev[6] = sysdev[6];
        this.sysdev[7] = sysdev[7];
        this.sysdev[8] = sysdev[8];
        this.sysdev[9] = sysdev[9];
    }

    //setter and getter
    public String[] getSysdev() {
        return sysdev;
    }

    public void setSysdev(String[] sysdev) {
        this.sysdev = sysdev;
    }

    @Override
    public String toString() {
        return "projectJava{" +
                "sysdev=" + Arrays.toString(sysdev) +
                '}';
    }
}
