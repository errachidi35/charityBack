package com.giveandgo.association.controller;

import com.giveandgo.association.entities.Membre;
import com.giveandgo.association.service.MembreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/membres")
public class MembreController {
    private final MembreService membreService;

    public MembreController(MembreService membreService) {
        this.membreService = membreService;
    }

    @PostMapping
    public Membre createMembre(@RequestBody Membre membre) {
        return membreService.createMembre(membre);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Membre> getMembreById(@PathVariable Long id) {
        Optional<Membre> membre = membreService.getMembreById(id);
        return membre.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/role/{role}")
    public List<Membre> getMembresByRole(@PathVariable String role) {
        return membreService.getMembresByRole(role);
    }

    @GetMapping
    public List<Membre> getAllMembres() {
        return membreService.getAllMembres();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMembre(@PathVariable Long id) {
        membreService.deleteMembre(id);
        return ResponseEntity.ok().build();
    }
}