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
    private CategorieMission type; // "DISALIMENT", "NETTESPPUB", "COLLECTALIMENT"

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
        this.type = type;
        this.responsable = responsable;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNbParticipants() {
        return nbParticipants;
    }

    public void setNbParticipants(Integer nbParticipants) {
        this.nbParticipants = nbParticipants;
    }

    public CategorieMission getType() {
        return type;
    }

    public void setType(CategorieMission type) {
        this.type = type;
    }

    public Membre getResponsable() {
        return responsable;
    }

    public void setResponsable(Membre responsable) {
        this.responsable = responsable;
    }

    public List<Participation> getParticipations() {
        return participations;
    }

    public void setParticipations(List<Participation> participations) {
        this.participations = participations;
    }

    public Discussion getDiscussion() {
        return discussion;
    }

    public void setDiscussion(Discussion discussion) {
        this.discussion = discussion;
    }

    public List<Don> getDons() {
        return dons;
    }

    public void setDons(List<Don> dons) {
        this.dons = dons;
    }
}