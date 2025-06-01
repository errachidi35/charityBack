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
public class Membre extends Utilisateur {
    @JoinColumn(nullable = false)
    private String domaine;

    private LocalDate dateInscription;

    @OneToMany(mappedBy = "responsable", fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Mission> missions;

    public Membre() {
        setRole("MEMBRE");
        this.missions = new ArrayList<>();
    }

    public Membre(String email, String motDePasse, String nom, String prenom, String domaine) {
        super(email, motDePasse, nom, prenom);
        setRole("MEMBRE");
        this.domaine = domaine;
        this.dateInscription = LocalDate.now();
        this.missions = new ArrayList<>();
    }
}