package com.store.bookstore.dto;

public class AuthResponse {
    private String message;
    private ResponseUserdto responseUserdto;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseUserdto getResponseUserdto() {
        return responseUserdto;
    }

    public void setResponseUserdto(ResponseUserdto responseUserdto) {
        this.responseUserdto = responseUserdto;
    }
}
