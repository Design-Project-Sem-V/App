package com.umang_rathod.hms;

import java.util.Date;

public class UserSchema {
    private String name;
    private Date birthday;
    private String email;
    private String password;
    private String gender;
    private String phone;
    private String bloodgroup;

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public UserSchema(String name, Date birthday, String email, String password, String gender, String phone, String bloodgroup) {
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.phone = phone;
        this.bloodgroup = bloodgroup;
    }
}
