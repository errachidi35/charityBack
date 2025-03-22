package com.giveandgo.association.controller;

import com.giveandgo.association.dto.RegisterRequest;
import com.giveandgo.association.model.Benevole;
import com.giveandgo.association.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        try {
            Benevole benevole = utilisateurService.registerUser(request);
            return ResponseEntity.ok("User registered successfully with ID: " + benevole.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        }
    }

}