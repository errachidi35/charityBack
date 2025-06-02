package com.wehelp.association.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    // Mission.java
@Column(nullable = true)
private String subtitle;

    private String description;
    private Integer nbParticipants;
    @Column(nullable = false)
    private Double goal;
    @Column(nullable = false)
    private Double raised = 0.0;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "typeMission", nullable = false)
    private CategorieMission typeMission; // "DISALIMENT", "NETTESPPUB", "COLLECTALIMENT"

    @ManyToOne
    @JoinColumn(name = "idResponsable", nullable = false)
    private Membre responsable;

    @OneToMany(mappedBy = "mission")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Participation> participations = new ArrayList<>();

    @OneToOne(mappedBy = "mission", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Discussion discussion;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Don> dons = new ArrayList<>();


    public Mission() {
        this.date = LocalDate.now();
    }

    public Mission(String nom, String lieu, String description, String subtitle, Integer nbParticipants, CategorieMission type, Membre responsable, Double goal) {
    this.nom = nom;
    this.date = LocalDate.now();
    this.lieu = lieu;
    this.description = description;
    this.subtitle = subtitle;
    this.nbParticipants = nbParticipants;
    this.typeMission = type;
    this.responsable = responsable;
    this.goal = goal;
    this.raised = 0.0;
}

}