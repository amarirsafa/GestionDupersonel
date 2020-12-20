package com.example.gestiondupersonel.classes;

public class Department {
    private int numberOfEmployees;
    private Employee headChef;
    private String title;

    public Department(){}

    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public void setHeadChef(Employee headChef) {
        this.headChef = headChef;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public Employee getHeadChef() {
        return headChef;
    }

    public String getTitle() {
        return title;
    }
}
