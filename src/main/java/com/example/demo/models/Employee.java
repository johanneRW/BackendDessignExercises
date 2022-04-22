package com.example.demo.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Employee {
    private int empId;
    private String empName;
    private String job;
    private int manger;
    private LocalDate hireDate;
    private int salary;
    private int commission;
    private int depID;
    private DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");;


    public Employee(int empId, String empName, String job, int manger, String hireDateAsString, int salary, int commission, int depID) {
        this.empId = empId;
        this.empName = empName;
        this.job = job;
        this.manger = manger;
        this.hireDate = getHireDateFromString(hireDateAsString);
        this.salary = salary;
        this.commission = commission;
        this.depID = depID;
    }



    public Employee(String empName, String job, int manger, String hireDateAsString, int salary, int commission, int depID) {
        this.empName = empName;
        this.job = job;
        this.manger = manger;
        this.hireDate = getHireDateFromString(hireDateAsString);
        this.salary = salary;
        this.commission = commission;
        this.depID = depID;
    }

    private LocalDate getHireDateFromString(String hireDateAsString) {

        LocalDate hireDate= LocalDate.parse(hireDateAsString,dateTimeFormatter);
        return hireDate;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getManger() {
        return manger;
    }

    public void setManger(int manger) {
        this.manger = manger;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDateAsString) {

        this.hireDate = this.hireDate = getHireDateFromString(hireDateAsString);
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getCommission() {
        return commission;
    }

    public void setCommission(int commission) {
        this.commission = commission;
    }

    public int getDepID() {
        return depID;
    }

    public void setDepID(int depID) {
        this.depID = depID;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", job='" + job + '\'' +
                ", manger=" + manger +
                ", hireDate=" + hireDate +
                ", salary=" + salary +
                ", commission=" + commission +
                ", depID=" + depID +
                '}';
    }
}
