package com.wehelp.association.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Admin extends Utilisateur {
    public Admin() {
        setRole("ADMIN");
    }

    public Admin(String email, String motDePasse, String nom, String prenom) {
        super(email, motDePasse, nom, prenom);
        setRole("ADMIN");
    }
}