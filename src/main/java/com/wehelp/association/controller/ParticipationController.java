package com.wehelp.association.controller;

import com.wehelp.association.entities.Benevole;
import com.wehelp.association.entities.Participation;
import com.wehelp.association.entities.Utilisateur;
import com.wehelp.association.service.ParticipationService;
import com.wehelp.association.service.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/participation")

public class ParticipationController {
    private final ParticipationService participationService;
    private final UtilisateurService utilisateurService;

    public ParticipationController(ParticipationService participationService, UtilisateurService utilisateurService) {
        this.participationService = participationService;
        this.utilisateurService = utilisateurService;
    }
    

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public Participation createParticipation(@RequestBody Participation participation) {
        return participationService.createParticipation(participation);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('BENEVOLE') or hasRole('MEMBRE') or hasRole('ADMIN')")
    public ResponseEntity<Participation> getParticipationById(@PathVariable Long id) {
        Optional<Participation> participation = participationService.getParticipationById(id);
        return participation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/benevole/{idBenevole}")
    @PreAuthorize("hasRole('BENEVOLE') or hasRole('ADMIN')")
    public List<Participation> getParticipationsByBenevole(@PathVariable Long idBenevole) {
        return participationService.getParticipationsByBenevole(idBenevole);
    }

    @GetMapping("/mission/{idMission}")
    @PreAuthorize("hasRole('MEMBRE') or hasRole('ADMIN')")
    public List<Participation> getParticipationsByMission(@PathVariable Long idMission) {
        return participationService.getParticipationsByMission(idMission);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Participation> getAllParticipations() {
        return participationService.getAllParticipations();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteParticipation(@PathVariable Long id) {
        participationService.deleteParticipation(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/check/{missionId}")
    @PreAuthorize("hasRole('BENEVOLE')")
    public ResponseEntity<Boolean> checkParticipation(@PathVariable Long missionId, Authentication auth) {
        String email = auth.getName();
        Utilisateur user = utilisateurService.findByEmail(email);

        if (!(user instanceof Benevole)) return ResponseEntity.status(403).build();

        boolean isParticipant = participationService
            .getParticipationsByMission(missionId)
            .stream()
            .anyMatch(p -> p.getBenevole().getId().equals(user.getId()));

        return ResponseEntity.ok(isParticipant);
    }

    @GetMapping("/benevole/{idBenevole}/mission/{idMission}")
@PreAuthorize("hasRole('BENEVOLE')")
public boolean hasParticipated(@PathVariable Long idBenevole, @PathVariable Long idMission) {
    return participationService.hasParticipated(idBenevole, idMission);
}


    @GetMapping("/mymissions")
    @PreAuthorize("hasRole('BENEVOLE')")
    public List<Long> getMissionIdsForCurrentBenevole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Benevole benevole = (Benevole) utilisateurService.findByEmail(email);
        return participationService.getParticipationsByBenevole(benevole.getId())
                .stream()
                .map(p -> p.getMission().getId())
                .toList();
    }


}