package com.umang_rathod.hms;

public class DoctorDetails {
    String name;
    String degree;
    String speciality;
    int experience;
    String mobile;
    String wMobile;
    int imgid;

    public DoctorDetails(String name, String degree, String speciality, int experience, String mobile, String wMobile, int imgid) {
        this.name = name;
        this.degree = degree;
        this.speciality = speciality;
        this.experience = experience;
        this.mobile = mobile;
        this.wMobile = wMobile;
        this.imgid = imgid;
    }

    public DoctorDetails() {}
}
