package com.bridgelabz.employeepayroll.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public @Data class EmployeeDTO {
    // Attributes
    private String name;
    private double salary;
}
