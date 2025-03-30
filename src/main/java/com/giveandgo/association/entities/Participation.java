package com.giveandgo.association.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    public Participation(Benevole benevole, Mission mission){
        this.benevole = benevole;
        this.mission = mission;
        this.dateInscription = LocalDate.now();
    }
}
