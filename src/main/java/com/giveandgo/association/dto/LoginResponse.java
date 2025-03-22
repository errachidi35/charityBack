package com.giveandgo.association.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private Long userId;
    private String message;

    public LoginResponse(Long userId, String message) {
        this.userId = userId;
        this.message = message;
    }
}