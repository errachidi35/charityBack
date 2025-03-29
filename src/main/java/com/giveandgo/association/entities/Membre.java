package com.giveandgo.association.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Membre extends Utilisateur {
    private String roleUtilisateur; // Renommé pour éviter la confusion avec le role de sécurité
    private String domaine;
    private LocalDate dateInscription;

    @OneToMany(mappedBy = "responsable")
    private List<Mission> missions = new ArrayList<>();

    public Membre() {
        setRole("ROLE_MEMBRE");
    }

    public Membre(String email, String motDePasse, String nom, String prenom, String roleUtilisateur, String domaine) {
        super(email, motDePasse, nom, prenom);
        setRole("ROLE_MEMBRE");
        this.roleUtilisateur = roleUtilisateur;
        this.domaine = domaine;
        this.dateInscription = LocalDate.now();
    }
}