package com.wehelp.association.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private int code;
    private String message;
    private String token;
    private Long id;

    private String role; // ← ajoute ce champ

    // constructeur avec tous les champs
    public LoginResponse(int code, String message, String token, Long id, String role) {
        this.code = code;
        this.message = message;
        this.token = token;
        this.id = id;
        
        this.role = role;

    }
}