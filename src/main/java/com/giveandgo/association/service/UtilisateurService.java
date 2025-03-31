package com.giveandgo.association.service;

import com.giveandgo.association.dto.LoginRequest;
import com.giveandgo.association.dto.LoginResponse;
import com.giveandgo.association.dto.MembreRegister;
import com.giveandgo.association.dto.RegisterRequest;
import com.giveandgo.association.entities.Benevole;
import com.giveandgo.association.entities.Membre;
import com.giveandgo.association.entities.Utilisateur;
import com.giveandgo.association.repository.MembreRepository;
import com.giveandgo.association.repository.UtilisateurRepository;
import com.giveandgo.association.security.JwtUtil;

import jakarta.validation.Validator;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final Validator validator; // Pour valider les contraintes
    private final MembreRepository membreRepository;
    private final JwtUtil jwtUtil;

    public UtilisateurService(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder, Validator validator, MembreRepository membreRepository, JwtUtil jwtUtil) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
        this.validator = validator;
        this.membreRepository = membreRepository;
        this.jwtUtil = jwtUtil;
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
        // Create a new Benevole entity
        Benevole benevole = new Benevole();
        benevole.setNom(request.getNom());
        benevole.setPrenom(request.getPrenom());
        benevole.setEmail(request.getEmail());
        benevole.setMotDePasse(passwordEncoder.encode(request.getMotDePasse())); // Encode the password
        benevole.setAdresse(request.getAdresse());
        benevole.setTelephone(request.getTelephone());
        benevole.setCompetences("");
        benevole.setHeuresContribuees(0.0f);
        benevole.setRole("BENEVOLE");
        benevole.setEnabled(true);
        benevole.setAccountLocked(false);

        // Save the user to the database
        return utilisateurRepository.save(benevole);
    }


    public LoginResponse login(LoginRequest request) {
        // Rechercher l'utilisateur par email
        Utilisateur utilisateur = utilisateurRepository.findByEmail(request.getEmail());
        if (utilisateur == null) {
            System.out.println("Utilisateur non trouvé avec l'email : " + request.getEmail());
            return new LoginResponse(-1, "Utilisateur non trouvé avec l'email : " + request.getEmail(), "");
        }

        // Vérifier le mot de passe
        if (!passwordEncoder.matches(request.getMotDePasse(), utilisateur.getMotDePasse())) {
            System.out.println("Mot de passe incorrect");
            return new LoginResponse(-1, "Mot de passe incorrect", "");
        }

        // Déterminer le rôle en fonction du type d'utilisateur
        String role = utilisateur.getClass().getSimpleName().toUpperCase(); // ADMIN, MEMBRE, BENEVOLE
        String token = jwtUtil.generateToken(utilisateur.getEmail(), role);

        // Retourner une réponse de succès
        return new LoginResponse(1, "Connexion réussie", token);
    }


    public Membre createMembreByAdmin(MembreRegister request) {

        // Créer un nouveau membre
        Membre membre = new Membre(); // Create a new Membre entity et le role est MEMBRE par défaut
        membre.setNom(request.getNom());
        membre.setPrenom(request.getPrenom());
        membre.setEmail(request.getEmail());
        membre.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
        membre.setAdresse(request.getAdresse());
        membre.setTelephone(request.getTelephone());

        return membreRepository.save(membre);
    }
}