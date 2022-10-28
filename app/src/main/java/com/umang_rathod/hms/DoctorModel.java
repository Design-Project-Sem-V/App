package com.umang_rathod.hms;

public class DoctorModel {
    int img;
    String name,degree,speciality,experience;
    DoctorModel(int img, String name, String degree, String speciality, String experience){
        this.img = img;
        this.name = name;
        this.degree = degree;
        this.speciality = speciality;
        this.experience = experience;
    }
}
