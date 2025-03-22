package com.giveandgo.association.dto;

import lombok.Data;

@Data
public class DonRequest {
    private String nomDonateur;
    private double montant;
    private Long missionId;
}