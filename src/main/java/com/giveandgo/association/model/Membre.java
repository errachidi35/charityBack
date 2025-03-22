package com.giveandgo.association.model;

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
        setRole("MEMBRE");
    }

    public Membre(String email, String motDePasse, String nom, String prenom, String roleUtilisateur, String domaine) {
        super(email, motDePasse, nom, prenom);
        setRole("MEMBRE");
        this.roleUtilisateur = roleUtilisateur;
        this.domaine = domaine;
        this.dateInscription = LocalDate.now();
    }

    public String getRoleUtilisateur() {
        return roleUtilisateur;
    }

    public void setRoleUtilisateur(String roleUtilisateur) {
        this.roleUtilisateur = roleUtilisateur;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public LocalDate getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
    }

    public List<Mission> getMissions() {
        return missions;
    }

    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }
}