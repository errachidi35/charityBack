package com.giveandgo.association.controller;

import com.giveandgo.association.entities.Benevole;
import com.giveandgo.association.service.BenevoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/benevole")
public class BenevoleController {
    private final BenevoleService benevoleService;

    public BenevoleController(BenevoleService benevoleService) {
        this.benevoleService = benevoleService;
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