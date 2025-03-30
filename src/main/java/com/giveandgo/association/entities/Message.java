package com.giveandgo.association.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idUtilisateur")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "idDiscussion")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Discussion discussion;

    private String contenu;
    private LocalDate dateEnvoi;


    public Message() {
        this.dateEnvoi = LocalDate.now();
    }

    public Message(Utilisateur utilisateur, Discussion discussion, String contenu) {
        this.utilisateur = utilisateur;
        this.discussion = discussion;
        this.contenu = contenu;
        this.dateEnvoi = LocalDate.now();
    }
}
