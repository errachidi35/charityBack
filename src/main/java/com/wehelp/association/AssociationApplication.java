package com.wehelp.association;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.wehelp.association.service.UtilisateurService;

@SpringBootApplication
public class AssociationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssociationApplication.class, args);
	}

	@Bean
    CommandLineRunner initAdmin(UtilisateurService utilisateurService) {
        return args -> {
            String email = "admin@wehelp.com";
            if (utilisateurService.findByEmail(email) == null) {
                utilisateurService.registerAdmin(email, "admin123", "Errachidi", "Abdelghafour");
                System.out.println("✅ Admin enregistré avec succès");
            } else {
                System.out.println("⚠️ Admin déjà présent");
            }
        };
    }

}
