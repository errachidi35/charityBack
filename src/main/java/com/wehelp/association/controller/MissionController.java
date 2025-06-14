 package com.wehelp.association.controller;

import com.wehelp.association.dto.DonRequest;
import com.wehelp.association.dto.MissionRequest;
import com.wehelp.association.dto.ParticipationRequest;
import com.wehelp.association.entities.*;
import com.wehelp.association.repository.MembreRepository;
import com.wehelp.association.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/mission")
public class MissionController {

    private final ParticipationService participationService;

    private final MissionService missionService;

    private final DonService donService;

    private final UtilisateurService utilisateurService;
    private final MembreRepository membreRepository;

    public MissionController(MissionService missionService, DonService donService, ParticipationService participationService, MembreService membreService, UtilisateurService utilisateurService, MembreRepository membreRepository) {
        this.donService = donService;
        this.missionService = missionService;
        this.participationService = participationService;
        this.utilisateurService = utilisateurService;
        this.membreRepository = membreRepository;
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
    public ResponseEntity<Mission> donate(@RequestBody DonRequest request) {
        Optional<Mission> missionOpt = missionService.getMissionById(request.getMissionId());
        if (missionOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Mission mission = missionOpt.get();

        // Création du don
        Don don = new Don(mission, request.getNomDonateur(), request.getMontant(), request.getMoyenPaiement());
        donService.createDon(don); // ça mettra à jour la mission.raise

        // Retourne la mission mise à jour
        return ResponseEntity.ok(missionService.getMissionById(mission.getId()).get());
    }


    @GetMapping("/type/{type}")
    public List<Mission> getMissionsByType(@PathVariable String type) {
        return missionService.getMissionsByType(type);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('MEMBRE')")
    public Mission createMission(@RequestBody MissionRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Membre membre = (Membre) utilisateurService.findByEmail(email);
// MissionController.java > createMission

Mission mission = new Mission(
    request.getNom(),
    request.getLieu(),
    request.getDescription(),
    request.getSubtitle(),  // 👈 ajouté ici
    request.getNbParticipants(),
    CategorieMission.valueOf(request.getType()),
    membre,
    request.getGoal()
);

        List<Mission> missions = membre.getMissions();
        missions.add(mission);
        membre.setMissions(missions);
        membreRepository.save(membre);
        return missionService.createMission(mission);
    }

    @PostMapping("/participate")
  @PreAuthorize("hasRole('BENEVOLE')")

    public Participation participate(@RequestBody ParticipationRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Benevole benevole = (Benevole) utilisateurService.findByEmail(email);
        Optional<Mission> mission = missionService.getMissionById(request.getIdMission());
        Participation participation = new Participation(benevole, mission.get());
        return participationService.createParticipation(participation);
    }




    @GetMapping("/responsable/{idResponsable}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBRE')")
    public List<Mission> getMissionsByResponsable(@PathVariable Long idResponsable) {
        return missionService.getMissionsByResponsable(idResponsable);
    }

    @GetMapping("/{id}/participants")
@PreAuthorize("hasRole('MEMBRE') or hasRole('ADMIN')")
public ResponseEntity<List<Participation>> getParticipationsByMission(@PathVariable Long id) {
    return ResponseEntity.ok(participationService.getParticipationsByMission(id));
}

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMission(@PathVariable Long id) {
        missionService.deleteMission(id);
        return ResponseEntity.ok().build();
    }
}