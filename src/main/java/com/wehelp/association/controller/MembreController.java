package com.wehelp.association.controller;

import com.wehelp.association.entities.Membre;
import com.wehelp.association.service.MembreService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/membre")
public class MembreController {
    private final MembreService membreService;

    public MembreController(MembreService membreService) {
        this.membreService = membreService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
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

    @GetMapping("/all")
    public List<Membre> getAllMembres() {
        return membreService.getAllMembres();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMembre(@PathVariable Long id) {
        membreService.deleteMembre(id);
        return ResponseEntity.ok().build();
    }
}