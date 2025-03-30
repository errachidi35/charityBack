package com.giveandgo.association.entities;

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
public class Discussion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "idMission", nullable = false)
    private Mission mission;

    private LocalDate dateCreation;

    @OneToMany(mappedBy = "discussion")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Message> messages = new ArrayList<>();

    public Discussion() {
        this.dateCreation = LocalDate.now();
    }

    public Discussion(Mission mission) {
        this.mission = mission;
        this.dateCreation = LocalDate.now();
    }
}