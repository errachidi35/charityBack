package com.giveandgo.association.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MembreRegister {
    @NotBlank(message = "Le nom est requis")
    private String nom;

    @NotBlank(message = "Le prénom est requis")
    private String prenom;

    @NotBlank(message = "L'email est requis")
    private String email;

    @NotBlank(message = "Le mot de passe est requis")
    private String motDePasse;

    private String adresse;
    private String telephone;
}