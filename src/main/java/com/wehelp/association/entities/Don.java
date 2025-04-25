package com.wehelp.association.entities;

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
    @JoinColumn(name = "idMission", nullable = false)
    private Mission mission;

    @JoinColumn(nullable = false)
    private String nomDonateur;
    @JoinColumn(nullable = false)
    private Double montant;
    @JoinColumn(nullable = false)
    private LocalDate date;
    @JoinColumn(nullable = false)
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
