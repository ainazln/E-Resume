package com.example.resume;

import java.io.Serializable;
import java.util.Arrays;

public class curricularJava implements Serializable {
    //variable
    public String[] curricular;

    //default constructor
    public curricularJava(){

    }

    //constructor
    public curricularJava(String[] curricular) {
        this.curricular = new String[12];
        this.curricular[0] = curricular[0];
        this.curricular[1] = curricular[1];
        this.curricular[2] = curricular[2];
        this.curricular[3] = curricular[3];
        this.curricular[4] = curricular[4];
        this.curricular[5] = curricular[5];
        this.curricular[6] = curricular[6];
        this.curricular[7] = curricular[7];
        this.curricular[8] = curricular[8];
        this.curricular[9] = curricular[9];
        this.curricular[10] = curricular[10];
        this.curricular[11] = curricular[11];
    }

    //setter and getter
    public String[] getCurricular() {
        return curricular;
    }

    public void setCurricular(String[] curricular) {
        this.curricular = curricular;
    }

    @Override
    public String toString() {
        return "curricularJava{" +
                "curricular=" + Arrays.toString(curricular) +
                '}';
    }
}
