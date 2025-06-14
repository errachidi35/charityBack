package com.wehelp.association.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "participation", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_benevole", "id_mission"})
})
@Getter
@Setter
@NoArgsConstructor
public class Participation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idBenevole", nullable = false)
    private Benevole benevole;

    @ManyToOne
    @JoinColumn(name = "idMission", nullable = false)
    private Mission mission;

    @JoinColumn(nullable = false)
    private LocalDate dateInscription;

    public Participation(Benevole benevole, Mission mission){
        this.benevole = benevole;
        this.mission = mission;
        this.dateInscription = LocalDate.now();
    }
}
