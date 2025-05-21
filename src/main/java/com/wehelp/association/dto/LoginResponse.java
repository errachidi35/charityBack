package com.wehelp.association.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private int code;
    private String message;
    private String token;
    private String role;

    public LoginResponse(int code, String message, String token, String role) {
        this.code = code;
        this.message = message;
        this.token = token;
        this.role = role;
    }
}