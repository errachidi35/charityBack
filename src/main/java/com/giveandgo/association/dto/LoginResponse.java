package com.giveandgo.association.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private Long userId;
    private String message;
    private String token;

    public LoginResponse(Long userId, String message, String token) {
        this.userId = userId;
        this.message = message;
        this.token = token;
    }
}