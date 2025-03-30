package com.giveandgo.association.controller;

import com.giveandgo.association.entities.Participation;
import com.giveandgo.association.service.ParticipationService;
import org.springframework.http.ResponseEntity;
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
    public Participation createParticipation(@RequestBody Participation participation) {
        return participationService.createParticipation(participation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participation> getParticipationById(@PathVariable Long id) {
        Optional<Participation> participation = participationService.getParticipationById(id);
        return participation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/benevole/{idBenevole}")
    public List<Participation> getParticipationsByBenevole(@PathVariable Long idBenevole) {
        return participationService.getParticipationsByBenevole(idBenevole);
    }

    @GetMapping("/mission/{idMission}")
    public List<Participation> getParticipationsByMission(@PathVariable Long idMission) {
        return participationService.getParticipationsByMission(idMission);
    }

    @GetMapping("/all")
    public List<Participation> getAllParticipations() {
        return participationService.getAllParticipations();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipation(@PathVariable Long id) {
        participationService.deleteParticipation(id);
        return ResponseEntity.ok().build();
    }
}