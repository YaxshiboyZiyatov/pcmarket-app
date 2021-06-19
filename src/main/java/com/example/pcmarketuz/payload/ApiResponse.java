package com.example.pcmarketuz.payload;

import lombok.Data;

@Data
public class ApiResponse {
    private String message;
    private boolean success;
    private Object object;

    public ApiResponse(String message, boolean success, Object object) {
        this.message = message;
        this.success = success;
        this.object = object;
    }

    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
