package com.wehelp.association.service;

import jakarta.validation.Validator;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wehelp.association.dto.LoginRequest;
import com.wehelp.association.dto.LoginResponse;
import com.wehelp.association.dto.MembreRegister;
import com.wehelp.association.dto.RegisterRequest;
import com.wehelp.association.entities.Admin;
import com.wehelp.association.entities.Benevole;
import com.wehelp.association.entities.Membre;
import com.wehelp.association.entities.Utilisateur;
import com.wehelp.association.repository.MembreRepository;
import com.wehelp.association.repository.UtilisateurRepository;
import com.wehelp.association.security.JwtUtil;
import com.wehelp.association.dto.UpdateBenevoleRequest;

import java.time.LocalDate;
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

 public Admin registerAdmin(String email, String motDePasse, String nom, String prenom) {
    Admin admin = new Admin();
    admin.setEmail(email);
    admin.setMotDePasse(passwordEncoder.encode(motDePasse));
    admin.setNom(nom);
    admin.setPrenom(prenom);
    admin.setEnabled(true);
    admin.setAccountLocked(false);

    return utilisateurRepository.save(admin);
}



    



    public LoginResponse login(LoginRequest request) {
        // Rechercher l'utilisateur par email
        Utilisateur utilisateur = utilisateurRepository.findByEmail(request.getEmail());
        if (utilisateur == null) {
            System.out.println("Utilisateur non trouvé avec l'email : " + request.getEmail());
            return new LoginResponse(-1, "Utilisateur non trouvé avec l'email : " + request.getEmail(), "",null,null);
        }

        // Vérifier le mot de passe
        if (!passwordEncoder.matches(request.getMotDePasse(), utilisateur.getMotDePasse())) {
            System.out.println("Mot de passe incorrect");
            return new LoginResponse(-1, "Mot de passe incorrect", "",null,null);
        }

        // Déterminer le rôle en fonction du type d'utilisateur
        String role = utilisateur.getRole(); // directement le champ 'role' de l'utilisateur
        String token = jwtUtil.generateToken(utilisateur.getEmail(), role);

        // Retourner une réponse de succès
    return new LoginResponse(1, "Connexion réussie", token,utilisateur.getId(), utilisateur.getRole());
    }

    public Benevole updateBenevoleProfile(String email, UpdateBenevoleRequest request) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
        if (utilisateur == null || !(utilisateur instanceof Benevole)) {
            throw new IllegalArgumentException("Bénévole non trouvé avec l'email : " + email);
        }

        Benevole benevole = (Benevole) utilisateur;

        // Mettre à jour les champs modifiables
        benevole.setNom(request.getNom());
        benevole.setPrenom(request.getPrenom());
        benevole.setEmail(request.getEmail()); // Optionnel, attention si l'email est utilisé pour l'auth
        benevole.setTelephone(request.getTelephone());
        benevole.setAdresse(request.getAdresse());

        return utilisateurRepository.save(benevole);
}



    public Membre createMembreByAdmin(MembreRegister request) {

        // Créer un nouveau membre
        Membre membre = new Membre(); // Create a new Membre entity et le role est MEMBRE par défaut
        membre.setNom(request.getNom());
    membre.setPrenom(request.getPrenom());
    membre.setEmail(request.getEmail());
    membre.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
    membre.setDomaine(request.getDomaine()); 
    membre.setEnabled(true);
    membre.setAccountLocked(false);
    membre.setDateInscription(LocalDate.now());

        return membreRepository.save(membre);
    }
}