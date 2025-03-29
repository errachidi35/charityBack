package com.giveandgo.association.dto;

import lombok.Data;

@Data
public class MissionResponse {
    private Long id;
    private String nom;
    private String lieu;
    private String description;
    private Integer nbParticipants;
    private String type;
    private Long responsableId; // ID de l'utilisateur responsable
}