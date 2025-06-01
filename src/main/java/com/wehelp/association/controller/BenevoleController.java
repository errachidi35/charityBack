package com.wehelp.association.controller;

import com.wehelp.association.dto.UpdateBenevoleRequest;
import com.wehelp.association.entities.Benevole;
import com.wehelp.association.service.BenevoleService;
import com.wehelp.association.service.UtilisateurService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/benevole")
public class BenevoleController {
    private final BenevoleService benevoleService;
    private final UtilisateurService utilisateurService;

    public BenevoleController(BenevoleService benevoleService, UtilisateurService utilisateurService) {
        this.benevoleService = benevoleService;
        this.utilisateurService = utilisateurService;
    }


    @GetMapping("/profil")
    @PreAuthorize("hasRole('BENEVOLE') or hasRole('MEMBRE')")
    public ResponseEntity<Benevole> getProfil() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Benevole benevole = (Benevole) utilisateurService.findByEmail(email);
        return ResponseEntity.ok(benevole);
    }

    @PutMapping("/profil")
    @PreAuthorize("hasRole('BENEVOLE') or hasRole('MEMBRE')")
    public ResponseEntity<?> updateProfil(@RequestBody UpdateBenevoleRequest request) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String email = authentication.getName();

    Benevole updated = utilisateurService.updateBenevoleProfile(email, request);
    return ResponseEntity.ok(updated);
}



    @PostMapping("/update")
    @PreAuthorize("hasRole('BENEVOLE')")
    public Benevole createBenevole(@RequestBody Benevole benevole) {
        return benevoleService.createBenevole(benevole);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBRE')")
    public ResponseEntity<Benevole> getBenevoleById(@PathVariable Long id) {
        Optional<Benevole> benevole = benevoleService.getBenevoleById(id);
        return benevole.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/competence/{competence}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBRE')")
    public List<Benevole> getBenevolesByCompetence(@PathVariable String competence) {
        return benevoleService.getBenevolesByCompetence(competence);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Benevole> getAllBenevoles() {
        return benevoleService.getAllBenevoles();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteBenevole(@PathVariable Long id) {
        benevoleService.deleteBenevole(id);
        return ResponseEntity.ok().build();
    }
}