package com.bridgelabz.employeepayroll.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@AllArgsConstructor
public @Data class ResponseDTO {
    // Attributes
    private String message;
    private int statusCode;
    private Object data;
}
