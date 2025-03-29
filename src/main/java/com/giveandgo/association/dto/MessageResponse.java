package com.giveandgo.association.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MessageResponse {
    private Long id;
    private Long idUtilisateur;
    private String contenu;
    private LocalDate dateEnvoi;
}