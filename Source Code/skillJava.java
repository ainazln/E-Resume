package com.example.resume;

import java.io.Serializable;
import java.util.Arrays;

public class skillJava implements Serializable {
    //variable
    public String[] language;
    public String[] complang;
    public String[] software;
    public String[] hardware;
    public String[] technology;

    //default constructor
    public skillJava(){

    }

    //constructor
    public skillJava(String[] language, String[] complang, String[] software, String[] hardware, String[] technology) {
        this.language = new String[4];
        this.language[0] = language[0];
        this.language[1] = language[1];
        this.language[2] = language[2];
        this.language[3] = language[3];
        this.complang = new String[9];
        this.complang[0] = complang[0];
        this.complang[1] = complang[1];
        this.complang[2] = complang[2];
        this.complang[3] = complang[3];
        this.complang[4] = complang[4];
        this.complang[5] = complang[5];
        this.complang[6] = complang[6];
        this.complang[7] = complang[7];
        this.complang[8] = complang[8];
        this.software = new String[15];
        this.software[0] = software[0];
        this.software[1] = software[1];
        this.software[2] = software[2];
        this.software[3] = software[3];
        this.software[4] = software[4];
        this.software[5] = software[5];
        this.software[6] = software[6];
        this.software[7] = software[7];
        this.software[8] = software[8];
        this.software[9] = software[9];
        this.software[10] = software[10];
        this.software[11] = software[11];
        this.software[12] = software[12];
        this.software[13] = software[13];
        this.software[14] = software[14];
        this.hardware = new String[5];
        this.hardware[0] = hardware[0];
        this.hardware[1] = hardware[1];
        this.hardware[2] = hardware[2];
        this.hardware[3] = hardware[3];
        this.hardware[4] = hardware[4];
        this.technology = new String[2];
        this.technology[0] = technology[0];
        this.technology[1] = technology[1];
    }

    //setter and getter
    public String[] getLanguage() {
        return language;
    }

    public void setLanguage(String[] language) {
        this.language = language;
    }

    public String[] getComplang() {
        return complang;
    }

    public void setComplang(String[] complang) {
        this.complang = complang;
    }

    public String[] getSoftware() {
        return software;
    }

    public void setSoftware(String[] software) {
        this.software = software;
    }

    public String[] getHardware() {
        return hardware;
    }

    public void setHardware(String[] hardware) {
        this.hardware = hardware;
    }

    public String[] getTechnology() {
        return technology;
    }

    public void setTechnology(String[] technology) {
        this.technology = technology;
    }

    @Override
    public String toString() {
        return "skillJava{" +
                "language=" + Arrays.toString(language) +
                ", complang=" + Arrays.toString(complang) +
                ", software=" + Arrays.toString(software) +
                ", hardware=" + Arrays.toString(hardware) +
                ", technology=" + Arrays.toString(technology) +
                '}';
    }
}
