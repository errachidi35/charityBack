package com.giveandgo.association.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Participation> participations = new ArrayList<>();

    public Benevole() {
        setRole("ROLE_BENEVOLE");
    }

    public Benevole(String email, String motDePasse, String nom, String prenom, Float heuresContribuees, String competences) {
        super(email, motDePasse, nom, prenom);
        setRole("ROLE_BENEVOLE");
        this.heuresContribuees = heuresContribuees;
        this.competences = competences;
    }
}