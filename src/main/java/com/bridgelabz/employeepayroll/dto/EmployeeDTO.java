package com.bridgelabz.employeepayroll.dto;

public class EmployeeDTO {
    // Attributes
    private String name;
    private double salary;

    // Constructors
    public EmployeeDTO() {}

    public EmployeeDTO(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
