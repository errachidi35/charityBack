package com.giveandgo.association.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Benevole extends Utilisateur {
    private Float heuresContribuees;
    private String competences;

    @OneToMany(mappedBy = "benevole")
    private List<Participation> participations = new ArrayList<>();

    public Benevole() {
        setRole("BENEVOLE");
    }

    public Benevole(String email, String motDePasse, String nom, String prenom, Float heuresContribuees, String competences) {
        super(email, motDePasse, nom, prenom);
        setRole("BENEVOLE");
        this.heuresContribuees = heuresContribuees;
        this.competences = competences;
    }
}