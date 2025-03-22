package com.giveandgo.association.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Participation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idBenevole")
    private Benevole benevole;

    @ManyToOne
    @JoinColumn(name = "idMission")
    private Mission mission;

    private LocalDate dateInscription;
}
