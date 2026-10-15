package com.napier.sem;

public class Employee {
    private int emp_no;
    private String first_name;
    private String last_name;
    private double salary;
    private Department dept; // Associated department (Exercise 2)

    // Constructors
    public Employee(int emp_no, String first_name, String last_name) {
        this.emp_no = emp_no;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Employee(int emp_no, String first_name, String last_name, double salary) {
        this.emp_no = emp_no;
        this.first_name = first_name;
        this.last_name = last_name;
        this.salary = salary;
    }

    // Getters and Setters
    public int getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(int emp_no) {
        this.emp_no = emp_no;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }
}