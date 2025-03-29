package com.giveandgo.association.controller;

import com.giveandgo.association.entities.Benevole;
import com.giveandgo.association.service.BenevoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/benevoles")
public class BenevoleController {
    @Autowired
    private BenevoleService benevoleService;

    @PostMapping
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

    @GetMapping
    public List<Benevole> getAllBenevoles() {
        return benevoleService.getAllBenevoles();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBenevole(@PathVariable Long id) {
        benevoleService.deleteBenevole(id);
        return ResponseEntity.ok().build();
    }
}