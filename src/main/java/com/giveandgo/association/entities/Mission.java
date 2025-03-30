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
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private LocalDate date;
    private String lieu;
    private String description;
    private Integer nbParticipants;

    @Enumerated(EnumType.STRING)
    private CategorieMission typeMission; // "DISALIMENT", "NETTESPPUB", "COLLECTALIMENT"

    @ManyToOne
    @JoinColumn(name = "idResponsable")
    private Membre responsable;

    @OneToMany(mappedBy = "mission")
    private List<Participation> participations = new ArrayList<>();

    @OneToOne(mappedBy = "mission")
    private Discussion discussion;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<Don> dons = new ArrayList<>();

    public Mission() {
        this.date = LocalDate.now();
    }

    public Mission(String nom, LocalDate date, String lieu, String description, Integer nbParticipants, CategorieMission type, Membre responsable) {
        this.nom = nom;
        this.date = date;
        this.lieu = lieu;
        this.description = description;
        this.nbParticipants = nbParticipants;
        this.typeMission = type;
        this.responsable = responsable;
    }
}