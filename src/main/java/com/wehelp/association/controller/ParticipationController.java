package com.wehelp.association.controller;

import com.wehelp.association.entities.Participation;
import com.wehelp.association.service.ParticipationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/participation")
public class ParticipationController {
    private final ParticipationService participationService;

    public ParticipationController(ParticipationService participationService) {
        this.participationService = participationService;
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
}