package com.wehelp.association.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MissionRequest {
    @NotBlank(message = "Le nom de la mission est requis")
    private String nom;

    @NotBlank(message = "Le lieu de la mission est requis")
    private String lieu;

    @NotBlank(message = "La description de la mission est requise")
    private String description;

    @NotNull(message = "Le nombre de participants est requis")
    private int nbParticipants;

    @NotBlank(message = "Le type de mission est requis")
    private String type;
}