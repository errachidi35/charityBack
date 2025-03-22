package com.giveandgo.association.model;

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

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Discussion getDiscussion() {
        return discussion;
    }

    public void setDiscussion(Discussion discussion) {
        this.discussion = discussion;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public LocalDate getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(LocalDate dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }
}
