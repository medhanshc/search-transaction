package com.example.demo.dto;

import lombok.Data;

@Data
public class ApiError {
    private String errorMessage;
    private int errorCode;
}
