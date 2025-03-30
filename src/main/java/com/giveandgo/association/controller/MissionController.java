package com.giveandgo.association.controller;

import com.giveandgo.association.dto.DonRequest;
import com.giveandgo.association.dto.ParticipationRequest;
import com.giveandgo.association.entities.Benevole;
import com.giveandgo.association.entities.Don;
import com.giveandgo.association.entities.Mission;
import com.giveandgo.association.entities.Participation;
import com.giveandgo.association.service.BenevoleService;
import com.giveandgo.association.service.DonService;
import com.giveandgo.association.service.MissionService;
import com.giveandgo.association.service.ParticipationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/mission")
public class MissionController {

    private final ParticipationService participationService;

    private final MissionService missionService;

    private final DonService donService;

    private final BenevoleService benevoleService;

    public MissionController(MissionService missionService, DonService donService, ParticipationService participationService, BenevoleService benevoleService) {
        this.donService = donService;
        this.missionService = missionService;
        this.participationService = participationService;
        this.benevoleService = benevoleService;
    }

    @GetMapping("/all")
    public List<Mission> getAllMissions() {
        return missionService.getAllMissions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mission> getMissionById(@PathVariable Long id) {
        Optional<Mission> mission = missionService.getMissionById(id);
        return mission.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/donate")
    public Don donate(@RequestBody DonRequest request) {
        Optional<Mission> mission = missionService.getMissionById(request.getMissionId());
        Don don = new Don(mission.orElse(null), request.getNomDonateur(), request.getMontant(), request.getMoyenPaiement());
        return donService.createDon(don);
    }

    @GetMapping("/type/{type}")
    public List<Mission> getMissionsByType(@PathVariable String type) {
        return missionService.getMissionsByType(type);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBRE')")
    public Mission createMission(@RequestBody Mission mission) {
        return missionService.createMission(mission);
    }

    @PostMapping("/participate")
    @PreAuthorize("hasRole('BENEVOLE')")
    public Participation participate(@RequestBody ParticipationRequest request) {
        Optional<Benevole> benevole = benevoleService.getBenevoleById(request.getIdBenevole());
        Optional<Mission> mission = missionService.getMissionById(request.getIdMission());

        Participation participation = new Participation(benevole.get(), mission.get());
        return participationService.createParticipation(participation);
    }

    @GetMapping("/responsable/{idResponsable}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBRE')")
    public List<Mission> getMissionsByResponsable(@PathVariable Long idResponsable) {
        return missionService.getMissionsByResponsable(idResponsable);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBRE')")
    public ResponseEntity<Void> deleteMission(@PathVariable Long id) {
        missionService.deleteMission(id);
        return ResponseEntity.ok().build();
    }
}