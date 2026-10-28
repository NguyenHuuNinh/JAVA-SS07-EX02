package com.example.recruitpro.response;

public class ApiResponse<T> {
    private String status;
    private String message;
    private T data;

    // Constructor mặc định
    public ApiResponse() {}

    // Constructor có tham số
    public ApiResponse(String message, T data, String status) {
        this.message = message;
        this.data = data;
        this.status = status;
    }

    // Getters và Setters
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }
}