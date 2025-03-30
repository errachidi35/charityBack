package com.giveandgo.association.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {

    @Value("${jwt.expiration}")
    private long JWT_TOKEN_VALIDITY;
    // Clé secrète pour signer les tokens
    @Value("${jwt.secret}")
    private  String SECRET_KEY;
    

    // Générer la clé à partir de la chaîne secrète
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // Générer un token JWT
    public String generateToken(String email, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        return createToken(claims, email);
    }

    @SuppressWarnings("deprecation")
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(getSigningKey())
                .compact();
    }

    // Valider un token JWT
    public Boolean validateToken(String token, String email) {
        final String username = extractEmail(token);
        return (username.equals(email) && !isTokenExpired(token));
    }

    // Extraire l'email (subject) du token
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extraire le rôle du token
    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

    // Extraire une revendication (claim) du token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // Vérifier si le token est expiré
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}