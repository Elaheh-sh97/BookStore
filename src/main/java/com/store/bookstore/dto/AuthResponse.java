package com.store.bookstore.dto;

public class AuthResponse<T> {
    private String message;
    private String token;
    private T data;

    public AuthResponse(String message,String token, T data) {
        this.message = message;
        this.token=token;
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
