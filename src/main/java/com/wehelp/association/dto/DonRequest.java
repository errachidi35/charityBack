package com.wehelp.association.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DonRequest {

    @NotBlank(message = "Le nom du donateur est requis")
    private String nomDonateur;

    @NotBlank(message = "Le moyen de paiement est requis")
    private String moyenPaiement; // Par exemple, "CARTE", "PAYPAL", "VIREMENT"

    @NotNull(message = "Le montant est requis")
    private double montant;

    @NotNull(message = "L'ID de la mission est requis")
    private Long missionId;
}