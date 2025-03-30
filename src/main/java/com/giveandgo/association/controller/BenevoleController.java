package com.giveandgo.association.controller;

import com.giveandgo.association.entities.Benevole;
import com.giveandgo.association.service.BenevoleService;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/create")
    public Benevole createBenevole(@RequestBody Benevole benevole) {
        return benevoleService.createBenevole(benevole);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Benevole> getBenevoleById(@PathVariable Long id) {
        Optional<Benevole> benevole = benevoleService.getBenevoleById(id);
        return benevole.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/competence/{competence}")
    public List<Benevole> getBenevolesByCompetence(@PathVariable String competence) {
        return benevoleService.getBenevolesByCompetence(competence);
    }

    @GetMapping("/all")
    public List<Benevole> getAllBenevoles() {
        return benevoleService.getAllBenevoles();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBenevole(@PathVariable Long id) {
        benevoleService.deleteBenevole(id);
        return ResponseEntity.ok().build();
    }
}