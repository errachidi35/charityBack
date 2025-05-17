package com.wehelp.association.controller;

import com.wehelp.association.dto.LoginRequest;
import com.wehelp.association.dto.LoginResponse;
import com.wehelp.association.dto.RegisterRequest;
import com.wehelp.association.entities.Benevole;
import com.wehelp.association.service.UtilisateurService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wehelp.association.dto.RegisterResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UtilisateurService utilisateurService;

    public AuthController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        try {
            Benevole benevole = utilisateurService.registerUser(request);
            RegisterResponse okResponse = new RegisterResponse(1, "User registered successfully with ID: " + benevole.getId());
            return ResponseEntity.ok(okResponse);
        } catch (Exception e) {
            RegisterResponse errResponse = new RegisterResponse(-1, e.getMessage());
            return ResponseEntity.ok(errResponse);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = utilisateurService.login(request);
        // Authentication authentication = authenticationManager.authenticate(
        //         new UsernamePasswordAuthenticationToken(request.getEmail(), request.getMotDePasse())
        // );
        // SecurityContextHolder.getContext().setAuthentication(authentication);

        // UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        // String jwt = jwtUtil.generateToken(userDetails);
        // String refreshToken = jwtUtil.generateRefreshToken(userDetails);

        // Map<String, Object> response = new HashMap<>();
        // response.put("message", "Connexion réussie");
        // response.put("token", jwt);
        return ResponseEntity.ok(response);
    }

}