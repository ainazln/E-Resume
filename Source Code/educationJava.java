package com.example.resume;

import java.io.Serializable;

public class educationJava implements Serializable {
    //variable
    public String edu;
    public String status;
    public String period;
    public String cgpa;
    public String edu1;
    public String status1;
    public String period1;
    public String cgpa1;

    //default constructor
    public educationJava(){

    }

    //constructor
    public educationJava(String edu, String status, String period, String cgpa, String edu1, String status1, String period1, String cgpa1) {
        this.edu = edu;
        this.status = status;
        this.period = period;
        this.cgpa = cgpa;
        this.edu1 = edu1;
        this.status1 = status1;
        this.period1 = period1;
        this.cgpa1 = cgpa1;
    }

    //setter and getter
    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getCgpa() {
        return cgpa;
    }

    public void setCgpa(String cgpa) {
        this.cgpa = cgpa;
    }

    public String getEdu1() {
        return edu1;
    }

    public void setEdu1(String edu1) {
        this.edu1 = edu1;
    }

    public String getStatus1() {
        return status1;
    }

    public void setStatus1(String status1) {
        this.status1 = status1;
    }

    public String getPeriod1() {
        return period1;
    }

    public void setPeriod1(String period1) {
        this.period1 = period1;
    }

    public String getCgpa1() {
        return cgpa1;
    }

    public void setCgpa1(String cgpa1) {
        this.cgpa1 = cgpa1;
    }

    @Override
    public String toString() {
        return "educationJava{" +
                "edu='" + edu + '\'' +
                ", status='" + status + '\'' +
                ", period='" + period + '\'' +
                ", cgpa='" + cgpa + '\'' +
                ", edu1='" + edu1 + '\'' +
                ", status1='" + status1 + '\'' +
                ", period1='" + period1 + '\'' +
                ", cgpa1='" + cgpa1 + '\'' +
                '}';
    }
}
