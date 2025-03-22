package com.giveandgo.association.service;

import com.giveandgo.association.dto.RegisterRequest;
import com.giveandgo.association.model.Benevole;
import com.giveandgo.association.model.Utilisateur;
import com.giveandgo.association.repository.UtilisateurRepository;
import jakarta.validation.Validator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final Validator validator; // Pour valider les contraintes

    public UtilisateurService(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder, Validator validator) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
        this.validator = validator;
    }

    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        // Valider l'objet utilisateur
        var violations = validator.validate(utilisateur);
        if (!violations.isEmpty()) {
            throw new IllegalArgumentException("Erreur de validation : " + violations);
        }

        // Vérifier si un utilisateur avec cet email existe déjà
        if (utilisateurRepository.findByEmail(utilisateur.getEmail()) != null) {
            throw new IllegalArgumentException("Un utilisateur avec cet email existe déjà : " + utilisateur.getEmail());
        }

        // Chiffrer le mot de passe avant de sauvegarder
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
        return utilisateurRepository.save(utilisateur);
    }

    public Optional<Utilisateur> getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id);
    }

    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public void deleteUtilisateur(Long id) {
        if (!utilisateurRepository.existsById(id)) {
            throw new IllegalArgumentException("Utilisateur non trouvé avec l'ID : " + id);
        }
        utilisateurRepository.deleteById(id);
    }

    public Utilisateur findByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }

    public Utilisateur getUtilisateurByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }

    public Benevole registerUser(RegisterRequest request) {
        // Validate role (only BENEVOLE is allowed for registration via the website)
        if (!"BENEVOLE".equals(request.getRole())) {
            throw new IllegalArgumentException("Autorisation refusée : Seuls les BENEVOLES sont autorisés à s'inscrire via le site web.");
        }

        // Create a new Benevole entity
        Benevole benevole = new Benevole();
        benevole.setNom(request.getNom());
        benevole.setPrenom(request.getPrenom());
        benevole.setEmail(request.getEmail());
        benevole.setMotDePasse(passwordEncoder.encode(request.getMotDePasse())); // Encode the password
        benevole.setAdresse(request.getAdresse());
        benevole.setTelephone(request.getTelephone());
        benevole.setRole("BENEVOLE");
        benevole.setEnabled(true);
        benevole.setAccountLocked(false);

        // Save the user to the database
        return utilisateurRepository.save(benevole);
    }
}