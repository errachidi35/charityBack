package com.giveandgo.association.controller;

import com.giveandgo.association.dto.LoginRequest;
import com.giveandgo.association.dto.LoginResponse;
import com.giveandgo.association.dto.RegisterRequest;
import com.giveandgo.association.entities.Benevole;
import com.giveandgo.association.service.UtilisateurService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UtilisateurService utilisateurService;

    public AuthController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        try {
            Benevole benevole = utilisateurService.registerUser(request);
            return ResponseEntity.ok("User registered successfully with ID: " + benevole.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        }
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = utilisateurService.login(request);
        return ResponseEntity.ok(response);
    }

}