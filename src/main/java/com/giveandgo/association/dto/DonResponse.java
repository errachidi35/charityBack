package com.giveandgo.association.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DonResponse {
    private Long id;
    private Double montant;
    private LocalDate dateDon;
    private String moyenPaiement;
    private String nomDonateur;
}