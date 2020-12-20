package com.example.gestiondupersonel.classes;

import java.util.Date;

public class Employee {
    private String CIN,address,email,fullName,function;
    private Date hiringDate,birthDay;
    private int salary;

    public Employee(){}

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setHiringDate(Date hiringDate) {
        this.hiringDate = hiringDate;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getCIN() {
        return CIN;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public Date getHiringDate() {
        return hiringDate;
    }

    public Date getBirthDay() {
        return birthDay;
    }
}


