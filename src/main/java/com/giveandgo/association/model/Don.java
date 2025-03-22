package com.giveandgo.association.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Don {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idMission")
    private Mission mission;

    private String nomDonateur;
    private Double montant;
    private LocalDate date;
    private String moyenPaiement;

    public Don() {
        this.date = LocalDate.now();
    }

    public Don(Mission mission, String nomDonateur, Double montant, String moyenPaiement) {
        this.mission = mission;
        this.nomDonateur = nomDonateur;
        this.montant = montant;
        this.date = LocalDate.now();
        this.moyenPaiement = moyenPaiement;
    }

    public Long getId() {
        return id;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public String getNomDonateur() {
        return nomDonateur;
    }

    public void setNomDonateur(String nomDonateur) {
        this.nomDonateur = nomDonateur;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getMoyenPaiement() {
        return moyenPaiement;
    }

    public void setMoyenPaiement(String moyenPaiement) {
        this.moyenPaiement = moyenPaiement;
    }
}
