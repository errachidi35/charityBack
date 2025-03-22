package com.giveandgo.association.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class Utilisateur implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom ne peut pas être vide")
    private String nom;

    @NotBlank(message = "Le prénom ne peut pas être vide")
    private String prenom;

    @NotBlank(message = "L'email ne peut pas être vide")
    @Email(message = "L'email doit être valide")
    private String email;

    private String telephone;

    private String adresse;

    @NotBlank(message = "Le mot de passe ne peut pas être vide")
    private String motDePasse;

    private String role; //  "ADMIN", "MEMBRE", "RBENEVOLE"

    private boolean enabled = true; // Pour gérer si le compte est activé
    private boolean accountLocked = false; // Pour gérer si le compte est verrouillé

    // Implémentation de UserDetails pour Spring Security
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role == null || role.isEmpty()) {
            return Collections.emptyList(); // Retourne une liste vide si le rôle est null ou vide
        }
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return motDePasse;
    }

    @Override
    public String getUsername() {
        return email; // Utilise l'email comme identifiant
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; 
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Utilisateur() {
    }

    public Utilisateur(String email, String motDePasse, String nom, String prenom) {
        this.email = email;
        this.motDePasse = motDePasse;
        this.nom = nom;
        this.prenom = prenom;
    }
}