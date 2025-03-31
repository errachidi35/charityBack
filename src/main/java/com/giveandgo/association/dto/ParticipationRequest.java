package com.giveandgo.association.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ParticipationRequest {
    @NotNull(message = "L'ID de la mission est requis")
    private Long idMission;
}