package com.wehelp.association.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBenevoleRequest {
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String adresse;
}
