package com.wehelp.association.entities;

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
    @JoinColumn(name = "idUtilisateur", nullable = false)
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "idDiscussion", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Discussion discussion;

    @JoinColumn(nullable = false)
    private String contenu;

    @JoinColumn(nullable = false)
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
