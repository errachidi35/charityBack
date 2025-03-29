package com.giveandgo.association.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MessageRequest {
    @NotNull(message = "L'ID de l'utilisateur est requis")
    private Long idUtilisateur;

    @NotBlank(message = "Le contenu du message est requis")
    private String contenu;
}