package com.giveandgo.association.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ParticipationResponse {
    private Long id;
    private Long idBenevole;
    private Long idMission;
    private LocalDate dateInscription;
}