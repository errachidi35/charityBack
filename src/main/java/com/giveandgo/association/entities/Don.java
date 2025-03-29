package com.giveandgo.association.entities;

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
}
