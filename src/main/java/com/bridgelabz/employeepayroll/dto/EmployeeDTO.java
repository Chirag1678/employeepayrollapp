package com.bridgelabz.employeepayroll.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
public @ToString class EmployeeDTO {
    // Attributes
    @NotEmpty(message = "Employee name cannot be null!!")
    @Pattern(regexp = "^[A-Z][a-zA-Z\\s]{2,}$", message = "Employee name is invalid")
    public String name;

    @Min(value=1000, message = "Minimum wager should be more than 1000")
    public double salary;

    @Pattern(regexp = "male|female", message = "Gender needs to be male or female")
    public String gender;

    @JsonFormat(pattern = "dd MM yyyy")
    @NotNull(message = "Start Date must not be null")
    @PastOrPresent(message = "Start Date must be in past or today's date")
    public LocalDate startDate;

    @NotBlank(message = "Note cannot be empty")
    public String note;

    @NotBlank(message = "ProfilePic cannot be empty")
    public String profilePic;

    @NotNull(message = "department must not be empty")
    public List<String> department;
}
